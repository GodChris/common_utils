# common_utils

#### 介绍
The project integrates basic generic tools, AOP, reflection, custom annotations to save development time.
一些通用工具，主要为了记录学习内容，同时方便个人开发使用

#### 软件架构
普通的maven项目，引入了spring-boot包，打包成jar包引入即可

#### 使用说明

1.  @XLog使用
（1）添加common扫描
         @ComponentScan({
               "com.godchris.common"
         })
（2）在需要打印日志的方法外加上@XLog注解，在方法执行前打印出方法的入参，及方法返回时打印出参
logkey为log的keyword
entityField为要打印的域，不填默认打印全部对象


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
