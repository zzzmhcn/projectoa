个人主页，欢迎留言
    http://zhangminghui.iok.la/

笔记
    DB具体设计笔记地址
    http://leanote.com/blog/post/5a82b593ab644140d2000fdf
    开发日志地址
    http://leanote.com/blog/post/5a7bb4d5ab6441766a0008f1
    Redis相关设计和实现方法
    http://leanote.com/blog/post/5a8e1f37ab64410bff0002ee
    Shiro相关
    http://leanote.com/blog/post/5a8e6b48ab64410bff000863

参考
    ityouknow的springboot系列 参考了他的第三、四、十四、二十章节 他官网里有他的源码GITHUB
    http://www.ityouknow.com/spring-boot.html
    使用spring-boot-admin对spring-boot服务进行监控
    http://blog.csdn.net/clementad/article/details/70613209
    详解html和thymeleaf中的相对路径，解决springboot前台页面的相对路径问题
    http://blog.csdn.net/qq_35603331/article/details/76255125
    彩虹猫启动画面
    https://raw.githubusercontent.com/snicoll-demos/spring-boot-4tw-uni/master/spring-boot-4tw-web/src/main/resources/banner.txt
    SpringBoot项目中使用redis缓存的方法步骤
    http://www.jb51.net/article/129775.htm
    开涛大神的《跟我学Shiro》系列
    http://jinnianshilongnian.iteye.com/blog/2018398
感谢
    该项目是由 程衫耘朵 和 张明辉 共同完成！
    特别要感谢云朵同学的鼎力相助！！！
    https://github.com/chsyd1028
    https://github.com/18121259693

改善空间
    1、calculator.js中存在监控键盘事件方法，会影响文字输入时候，按退格无效的情况。目前只在首页引入calculator.js，但还是影响了todoList备忘录的输入的删除
    2、shiro和springbootadmin冲突问题，目前只通过反过来配置shiro解决，使用起来差别不大，但不完美。
    3、手机端部分组件兼容性不强，例如表格在手机显示不理想。
    4、shiro未使用盐值加密。
    5、redis命名方式和存读方式还有优化空间。

注意
    初次使用修改配置文件projectoa\src\main\resources\application.properties
    spring.datasource.*** 配置MySql
    spring.redis.***      配置redis(如果是本机就不用配)
    logback.filepath      配置输出日志路径
    首次使用需要向数据库导入projectoa\database\dboa.sql文件

请使用IDEA 2017.03 或以上版本导入本项目，并且建议使用本地独立版本的gradle (4.4以上)