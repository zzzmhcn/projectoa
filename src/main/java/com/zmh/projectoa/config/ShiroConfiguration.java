package com.zmh.projectoa.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ChengShanyunduo
 * 2018/1/8
 */
@Configuration
@EnableTransactionManagement
public class ShiroConfiguration {


    private final Log logger = LogFactory.getLog(ShiroConfiguration.class);


    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm realm = new MyShiroRealm();
        //前台输入的为mD5加密的
        HashedCredentialsMatcher md5 = new HashedCredentialsMatcher("MD5");
        realm.setCredentialsMatcher(md5);
        return realm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(MyShiroRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm,多个realm可以用securityManager.setRealms();
        securityManager.setRealm(realm);
        //设置认证策略
        //ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        //securityManager.setAuthenticator(authenticator);
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    //shirofilter，使用springboot时name可以随设置，不需要context设置，
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //登录页面（请求）
        factoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接  （请求）
        factoryBean.setSuccessUrl("/index");
        //没有权限页面
        factoryBean.setUnauthorizedUrl("/403");
        loadShiroFilterChain(factoryBean);
        return factoryBean;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 加载ShiroFilter权限控制规则
     * 这块代码是负责拦截的
     * 也就是说，如果当前登陆的人，不符合访问某个url的访问权限条件的，直接给他弹开
     * 也就是分配谁能访问谁
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean factoryBean) {
        /**下面这些规则配置最好配置到配置文件中*/
        Map<String, String> filterChainMap = new LinkedHashMap<String, String>();
        /** authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器  
         * org.apache.shiro.web.filter.authc.FormAuthenticationFilter */
        // anon：它对应的过滤器里面是空的,什么都没做,可以理解为不拦截  
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/login/check", "anon");
        filterChainMap.put("/403", "anon");
        filterChainMap.put("/assets/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/prism/**", "anon");
        filterChainMap.put("/health/**", "anon");
        filterChainMap.put("/api/**", "anon");
        //权限分配
        filterChainMap.put("/admin/**", "authc,roles[admin]");
        filterChainMap.put("/user/**", "authc,roles[user]");

        //行为分配
        filterChainMap.put("/notice/notice_create", "authc,perms[notice]");
        filterChainMap.put("/notice/notice_send", "authc,perms[notice]");

        //登出的过滤器
        filterChainMap.put("/logout", "logout");

        filterChainMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
    }  
  
    /*1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。主要是AuthorizingRealm类的子类，以及EhCacheManager类。  
    2.HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，这个类也负责对form里输入的密码进行编码。  
    3.ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。  
    4.EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。  
    5.SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。  
    6.ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。  
    7.DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。  
    8.AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。*/
}
