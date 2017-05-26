java统计apk或者jar、dex方法数

首先需要下载一个工具类:

[java统计apk或者jar、dex方法数工具](https://github.com/chenshouyin/DevNote/tree/master/%E5%B7%A5%E5%85%B7%E6%8A%80%E5%B7%A7%E7%AF%87/java%E7%BB%9F%E8%AE%A1apk%E6%88%96%E8%80%85jar%E3%80%81dex%E6%96%B9%E6%B3%95%E6%95%B0)

下载之后,cd到此目录


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-300e9ee9bea6504b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

执行
```
java -jar E:\java统计apk或者jar、dex方法数\dex-method-counts.jar 待统计.dex
```


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-bb81a5da5a3b7444.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们可以看到已经成功的统计了该dex里面的方法数。



如果是apk呢，执行
```
java -jar E:\java统计apk或者jar、dex方法数\dex-method-counts.jar 待统计.apk
```

即可。


那么如果是jar包呢？是否也可以直接执行该命令?

```
java -jar E:\java统计apk或者jar、dex方法数\dex-method-counts.jar 待统计.jar
```



![030HI2%A{(~C{R%T[W5)$@I.png](http://upload-images.jianshu.io/upload_images/2704327-de41352a46f9b5bc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


答案是不行的，需要先将jar转换为dex,其实也是非常简单的，可以看下我之前的文章。

[Android将jar包转换为dex二进制文件](http://www.jianshu.com/p/562eaf211ff5)