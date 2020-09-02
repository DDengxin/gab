###部署依赖
0. window server 2012+ 
1. jdk1.8.x
2. kkFileView(文件预览必须)[https://kkfileview.keking.cn/zh-cn/index.html]
3. tomcat8.5(非必须,当以war包启动时必须)

### 开放端口
1. port:8012(kkFileView默认端口为8012如调整需同步调整)
2. port:80(服务器访问端口,默认8080)

### 如何启动
##jar包启动
```bash
//开发环境
java  
-Xms2048m -Xmx2048m -Xmn512m //jvm参数
-jar Platform_wireerp-0.0.1-SNAPSHOT.jar //启动文件
```


```bash
//生产环境
java  
-Xms2048m -Xmx2048m -Xmn512m //jvm参数
-jar Platform_wireerp-0.0.1-SNAPSHOT.jar //启动文件
--spring.profiles.active=prod-lnjd //spring配置文件
```
-Djava.security.egd=/dev/random
##war包启动
> 暂略