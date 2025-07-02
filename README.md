---
## 特别鸣谢

本项目 CDN 加速及安全防护由 Tencent EdgeOne 赞助

[![Tencent EdgeOne](https://edgeone.ai/media/34fe3a45-492d-4ea4-ae5d-ea1087ca7b4b.png)](https://edgeone.ai/zh?from=github)

---


# 个人主页

[https://zzzmh.cn/](https://zzzmh.cn/)

---

# 友情链接

[https://yunduo250.com/](https://yunduo250.com/)

---

# 关于本项目简介

[https://zzzmh.cn/single.jsp?id=2](https://zzzmh.cn/single.jsp?id=2)

---

# 线上预览地址

[https://zzzmh.cn/projectoa/index](https://zzzmh.cn/projectoa/index)
测试账号: `admin`
测试密码: `123456`

---

# 笔记

* **DB具体设计笔记地址**: [http://leanote.com/blog/post/5a82b593ab644140d2000fdf](http://leanote.com/blog/post/5a82b593ab644140d2000fdf)
* **开发日志地址**: [http://leanote.com/blog/post/5a7bb4d5ab6441766a0008f1](http://leanote.com/blog/post/5a7bb4d5ab6441766a0008f1)
* **Redis相关设计和实现方法**: [http://leanote.com/blog/post/5a8e1f37ab64410bff0002ee](http://leanote.com/blog/post/5a8e1f37ab64410bff0002ee)
* **Shiro相关**: [http://leanote.com/blog/post/5a8e6b48ab64410bff000863](http://leanote.com/blog/post/5a8e6b48ab64410bff000863)

---

# 参考

* **ityouknow的springboot系列**: 参考了他的第三、四、十四、二十章节，他官网里有他的源码GITHUB：[http://www.ityouknow.com/spring-boot.html](http://www.ityouknow.com/spring-boot.html)
* **使用spring-boot-admin对spring-boot服务进行监控**: [http://blog.csdn.net/clementad/article/details/70613209](http://blog.csdn.net/clementad/article/details/70613209)
* **详解html和thymeleaf中的相对路径，解决springboot前台页面的相对路径问题**: [http://blog.csdn.net/qq_35603331/article/details/76255125](http://blog.csdn.net/qq_35603331/article/details/76255125)
* **彩虹猫启动画面**: [https://raw.githubusercontent.com/snicoll-demos/spring-boot-4tw-uni/master/spring-boot-4tw-web/src/main/resources/banner.txt](https://raw.githubusercontent.com/snicoll-demos/spring-boot-4tw-uni/master/spring-boot-4tw-web/src/main/resources/banner.txt)
* **SpringBoot项目中使用redis缓存的方法步骤**: [http://www.jb51.net/article/129775.htm](http://www.jb51.net/article/129775.htm)
* **开涛大神的《跟我学Shiro》系列**: [http://jinnianshilongnian.iteye.com/blog/2018398](http://jinnianshilongnian.iteye.com/blog/2018398)

---

# 感谢

该项目是由 **程衫耘朵** 和 **张明辉** 共同完成！

特别要感谢**云朵**同学的鼎力相助！！！

* [https://github.com/chsyd1028](https://github.com/chsyd1028)
* [https://github.com/zzzmhcn](https://github.com/zzzmhcn)

---

# 改善空间

1.  `calculator.js` 中存在监控键盘事件方法，会影响文字输入时，按退格无效的情况。目前只在首页引入 `calculator.js`，但还是影响了 `todoList` 备忘录的输入删除。
2.  `Shiro` 和 `springbootadmin` 冲突问题，目前只通过反过来配置 `Shiro` 解决，使用起来差别不大，但不完美。
3.  手机端部分组件兼容性不强，例如表格在手机显示不理想。
4.  `Shiro` 未使用盐值加密。
5.  `Redis` 命名方式和存读方式还有优化空间。

---

# 注意

* 初次使用请修改配置文件 `projectoa\src\main\resources\application.properties`:
    * `spring.datasource.***`: 配置MySQL
    * `spring.redis.***`: 配置Redis (如果是本机就不用配)
    * `logback.filepath`: 配置输出日志路径
* 首次使用需要向数据库导入 `projectoa\database\dboa.sql` 文件。
* 请使用 **IDEA 2017.03 或以上版本** 导入本项目，并且建议使用本地独立版本的 **Gradle (4.4以上)**。
