Mac下Sunny_Ngrok内网地址映射成外网

我们本地Tomcat服务器访问的时候一般是127.0.0.1加端口号,比如:http://127.0.0.1:8080

但是这个只有统一局域网的设备才能访问,比如我们想发布一个外部网络也可以访问的web应用。那得去租用服务器？然后繁琐的配置？？no! no！no！


我们可以使用Sunny_Ngrok,方便的将内网地址映射成外网地址。当然还有另外一些工具比如 nat123,花生壳等,但是感觉还是Sunny_Ngrok使用方便。


使用方法

1.[下载ngrok工具](https://github.com/chenshouyin/DevNote/tree/master/%E5%90%8E%E5%8F%B0%E5%BC%80%E5%8F%91%E7%AF%87/Sunny-Ngrok%E5%86%85%E7%BD%91%E5%9C%B0%E5%9D%80%E6%98%A0%E5%B0%84%E6%88%90%E5%A4%96%E7%BD%91/mac%E4%B8%8Bweb%E6%9C%8D%E5%8A%A1%E5%99%A8%E6%90%AD%E5%BB%BA%E4%B9%8Bngrok%E7%AF%87)



![image.png](http://upload-images.jianshu.io/upload_images/2704327-60ac774433512a07.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


2.解压到单独的文件夹,并且命令行切换到此目录

3.启动映射服务
Linux或者Mac系统下启动，输入
```
./sunny clientid 隧道id
```
或者
```
./sunny clientid 隧道id1,隧道id2
```
启动多个服务映射


其中隧道id需要去[官网注册账号](http://www.ngrok.cc/login )，然后开通免费的隧道。

首先购买标价0元的服务器


![image.png](http://upload-images.jianshu.io/upload_images/2704327-53cb719edbba65fa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


再填写相关信息
![image.png](http://upload-images.jianshu.io/upload_images/2704327-7741afad1447adc4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



就可以得到隧道id了
![image.png](http://upload-images.jianshu.io/upload_images/2704327-4b4da867c463994e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



启动映射成功后,如图


![](http://upload-images.jianshu.io/upload_images/2704327-16725c7cc848010f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

此时既可以通过外网访问Tomcat服务器了


![image.png](http://upload-images.jianshu.io/upload_images/2704327-404077b94ac2ec22.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


上面介绍的是Mac平台下使用Sunny_Ngrok进行内网地址映射,Windows和其它平台的去官网 https://ngrok.cc 下载，操作类似。


[我的GitHub](http://blog.csdn.net/e_inch_photo)
[我的CSDN](https://github.com/chenshouyin?tab=repositories)
[我的简书](http://www.jianshu.com/u/303ec9abdc08)
[开发笔记](https://github.com/chenshouyin/DevNote)

如有不对之处,欢迎留言和批评指正!