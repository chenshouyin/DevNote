INSTALL_FAILED_UPDATE_INCOMPATIBLE

```
[2017-06-11 22:19:37 - BioCareView] Installing BioCareView.apk...
[2017-06-11 22:19:39 - BioCareView] Installation error: INSTALL_FAILED_UPDATE_INCOMPATIBLE
[2017-06-11 22:19:39 - BioCareView] Please check logcat output for more details.
[2017-06-11 22:19:39 - BioCareView] Launch canceled!
```

开发有时候莫名其妙的出现安装不上应用程序的情况,如上，刚开始以为是第三方手机软件占用了端口,但是关闭或者卸载其它第三方手机管理软件之后,还是安装不上。


最后想到了adb命令。


![image.png](http://upload-images.jianshu.io/upload_images/2704327-ecce8673249339cd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

但是试了一大堆命令还是没有效果。



最后还是通过adb卸载应用程序,居然就好了！！（这里不太明白为什么，因为原先手机里面已经卸载了该app,在应用程序列表也找不到，但是通过命令卸载之后居然好了，可能是原先卸载不干净吧。）

```
adb uninstall 程序的包名
```


![image.png](http://upload-images.jianshu.io/upload_images/2704327-9fda7dc07e220781.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


之后再重新在eclipse中run,就好了！！！


最后附如何在命令行使用adb

其实很简单

切换到adb所在的目录（或者把此目录配置在环境变量Path下）

如我的
```
D:\Android\android-sdk-windows\platform-tools
```
之后就可以输入adb命令啦。


[我的GitHub](http://blog.csdn.net/e_inch_photo)
[我的CSDN](https://github.com/chenshouyin?tab=repositories)
[我的简书](http://www.jianshu.com/u/303ec9abdc08)
[开发笔记](https://github.com/chenshouyin/DevNote)
