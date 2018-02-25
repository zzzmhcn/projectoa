package com.zmh.projectoa.config;

import com.zmh.projectoa.model.Users;
import com.zmh.projectoa.service.UsersService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * shiro的认证最终是交给了Realm进行执行
 * 所以我们需要自己重新实现一个Realm，此Realm继承AuthorizingRealm
 * Created by sun on 2017-4-2.
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Log logger = LogFactory.getLog(AuthorizingRealm.class);

    @Autowired
    UsersService usersService;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //2.从UsernamePasswordToken中来获取username
        String username = upToken.getUsername();
        //3.调用数据库的方法， 从数据库中查询username对应的用户记录
        Users temp = new Users();
        temp.setUsername(username);
        Users user = usersService.queryUserByUsername(temp);
        //4.若用户不存在， 则可抛出UnknownAccountException异常
        if (Objects.isNull(user)){
            throw new UnknownAccountException("用户不存在");
        }
        //5.根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
        if (!"0".equals(user.getIsDel())){
            throw new LockedAccountException("用户被锁定");
        }
        //6.根据用户的情况， 来构建AuthenticationInfo对象并返回， 通常使用的实现类为：SimpleAuthenticationInfo
        //以下信息是从数据库中获取的
        //1.principal:认证的实体信息，可以是username，也可以是数表对应的实体类对象
        Object principal = user;
        //2.creadentials： 密码
        //String pw = MD5Util.string2MD5("123456");
        Object credentials = user.getPassword();
        //3. realName: 当前对象的name，调用弗雷的getName()方法即可
        String realmName = user.getRealname();
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
        return info;
    }

    /**
     * 权限认证（为当前登录的Subject授予角色和权限）
     *
     * 该方法的调用时机为需授权资源被访问时，并且每次访问需授权资源都会执行该方法中的逻辑，这表明本例中并未启用AuthorizationCache，
     * 如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），
     * 超过这个时间间隔再刷新页面，该方法会被执行
     *
     * doGetAuthorizationInfo()是权限控制，
     * 当访问到页面的时候，使用了相应的注解或者shiro标签才会执行此方法否则不会执行，
     * 所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1.从PrincipalCollection中获取登陆用户的信息
        Object principal = principals.getPrimaryPrincipal();
        //2.利用登陆用户的信息来获取当前用户的角色或权限（可能需要查询数据库）
        Users user = (Users) principal;
        Set<String> roles = new HashSet<>();
        Integer departmentId = user.getDepartmentId();
        Integer positionId = user.getPositionId();
        //管理员权限
        if (departmentId == 1){
            roles.add("admin");
        }
        //人事权限
        if (departmentId == 2){
            roles.add("user");
        }
        //3.创建SimpleAuthorizationInfo， 并设置其roles属性。
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //4.返回对象
        return info;
    }
}
