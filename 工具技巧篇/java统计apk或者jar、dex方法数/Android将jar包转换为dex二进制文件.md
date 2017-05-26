通过dx工具将jar包中所有的类打包编译为一个名为classes.dex的文件。

该工具在

sdk的build-tools里面比如build-tools\19.1.0


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-c11604b239bb27bd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


cmd切换目录到该文件夹下,执行

```
dx --dex --output 待统计.dex 待统计.jar
```

注意 待统计.jar 也应该在该目录下,或者其它目录,如：
```
E:\java统计apk或者jar、dex方法数\待统计.jar
```


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-d223aab64cc786c5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


输入完命令,点击回车,等执行完后,在改目录已经生成了我们需要的dex文件。


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-a7f45c8e1867b6b2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


如果有兴趣的话可以继续看下一篇

[java统计apk或者jar、dex方法数](http://www.jianshu.com/p/84e4dd7214a4)