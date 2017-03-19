## 项目地址
https://github.com/minlingchao1/auth-center

## 项目说明
auth-center是一个轻量级的权限管理系统。其核心目标是实现公司内部各个系统的权限的集中管理,利用cas单点登录实现系统的一站式登录
## 特点
- 单点登录
- 权限集中管理，提供权限管理中心
- 集成第三方登录，与现有账号绑定，方便快捷
- 利用shiro实现权限管理，灵活快捷，可以控制到页面的菜单或者按钮，满足大部分的权限需求

## 架构
![这里写图片描述](http://img.blog.csdn.net/20170319230429247?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2hhb194dW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 项目介绍
项目主要分为auth-client,auth-server,cas-server三个项目,auth-client主要是用于客户端集成shiro使用,只需要简单的配置,便可以继承shiro,auth-server是权限管理中心,它主要管理各个系统的用户,角色，资源以及授权管理等各个功能,cas-server主要用于一站式登录，提供用户名密码和第三方登录两种形式,目前仅支持qq登录，如果想要集成其他第三方登录请自行添加。
## 配置文件
- 数据库配置
  jdbc.properties
- cas配置
  cas.properties
  
  `#登出跳转地址`
  `cas.logout.redirect.url=${cas.logout.redirect.url}`  
  

  `#登录地址`
  `cas.login.url=${cas.login.url}`

  `#登录服务器地址`. 
  
  
  `cas.server.url.prefix=${cas.server.url.prefix}`

  `#cas服务地址`. 
  
  `cas.service=${cas.service}`

  `#登录成功跳转地址`. 
  
  `cas.success.url=${cas.success.url}`
