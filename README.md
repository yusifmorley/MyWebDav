# MyWebDav
基于SpringBoot的WebDav服务器
### 环境依赖
java 8
Springboot 2.7.11
io.milton 3.0.0.180
### 配置
    webdav:
        folder: res #父路径 webdav文件夹父路径
        path: webdav # webdav文件夹路径 也是 webdav url路径
        username: root #用户名
        password: root #密码

    server
        port: 8080   // 跟随springboot配置
        address: 127.0.0.1 // 跟随springboot配置
### 使用
项目启动后访问两种使用webdav服务

1. 使用浏览器访问http://127.0.0.1:8080/webdav （浏览器只提供简单的文件浏览和下载）

2. 使用webdav客户端 根据项目配置使用
    