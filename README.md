## 项目地址
https://github.com/minlingchao1/auth-center

## 项目说明
auth-center是一个轻量级的权限管理系统。其核心目标是实现公司内部各个系统的权限的集中管理,利用cas单点登录实现系统的一站式登录
## 特点
- 单点登录
- 权限集中管理，提供权限管理中心
- 集成第三方登录，与现有账号绑定，方便快捷
- 利用shiro实现权限管理，灵活快捷，可以控制到页面的菜单或者按钮，满足大部分的权限需求


## 模块介绍
- auth-cas 单点登录服务器端,所有客户端的用户登录都需要跳转到cas服务器端。提供用户名密码登录和qq登录两种方式
- auth-cas-shiro-common shiro-cas整合需要的工具类,供所有客户端引入使用，只需要将其作为jar包引入即可
- auth-core 用于客户端从权限管理端获取信息使用
- auth-server 所有客户端的权限管理中心,包括角色分配，用户信息获取等功能

## 使用教程

### 多版本配置

主要针对web端的配置,这里指auth-server-web

首先在相应的pom.xml中的profiles节点添加如下代码:  
```
<profile>
    <id>dev</id>
    <properties>
        <env>版本名称</env>
    </properties>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
</profile>
```

在src/main.filters文件夹下创建版本名称-env.properties文件即可


### 数据库配置

#### 数据表说明
所需要的数据表均在auth-server/auth-server-core中的resource文件夹下

app:应用信息存储表
authorization:授权信息
resource:资源（菜单/按钮)
role:角色表
user:用户表
qq_user:qq用户信息
qq_user_ref:qq用户与用户表关联
verify_code:验证码发送表

#### 数据库连接配置
数据库配置文件 auth-server/auth-server-web的resource文件夹下 jdbc.properties

- jdbc.driver=com.mysql.jdbc.Driver
- jdbc.url=     //数据库地址
- jdbc.username=   //数据库用户名
- jdbc.password=   //密码
- jdbc.initialSize=0
- jdbc.maxTotal=20 //最大连接数
- jdbc.maxIdle=20
- jdbc.minIdle=1
- jdbc.maxWaitMillis=60000 //最大等待时间


### Redis配置
redis配置文件 auth-server/auth-server-web的resource文件夹下 redis.properties

- redis.host= //redis地址
- redis.port= //端口
- redis.password= //密码
- redis.expire=100000 
- redis.timeout=10000 //连接超时时间，不要设置的太小
- redis.default.db=6 //数据库


### 客户端连接cas配置
cas配置文件 auth-server/auth-server-web的resource文件夹下 cas.properties

- cas.logout.redirect.url= //退出重定向地址,格式:http://cas_server_url/logout?service=http://client_url_index/
- cas.login.url= //登录地址 格式 http://cas_server_url/login?service=http://client_url/cas
- cas.server.url.prefix= //cas服务器地址前缀 格式:http://cas_server_url
- cas.service= //客户端登录cas地址,格式 http://client_url/cas
- cas.success.url= //登录成功跳转地址 格式:http://client_url/index/success
- cas.app.key= //cas登录的应用密钥

### 启动

在tomcat中启动,默认端口8081