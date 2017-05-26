Eclipse简单分包65535问题

我们知道,再Android Studio中解决分包问题是非常简单的,但是一搜eclipse中分包实现,各种结果,大部分都是使用Gradle进行编译实现自动分包，比如这篇 [Eclipse中使用multidex解决分包](http://www.jianshu.com/p/f7f28e1e3ce3)
,配置一大堆,非常麻烦。


首先google官方是给出了解决方案的，详细请看连接：

https://developer.android.google.cn/studio/build/multidex.html

最核心的技术就是：Dalvik 可执行文件分包。我们都知道安卓的虚拟机能执行的就是 dex文件，dex 说白了就是 class文件 通过工具进行相应的转换的。google 这个解决方案当然是针对自己的亲儿子 AndroidStudio 解决的，对于 eclipse 可不管。studio 本身用的就是 gradle 编译的。但是 eclipse 默认是 ant 编译的所以，还需要配置 gradle ，但是 eclipse 配置 gradle 还是相对比较麻烦的。所以我们还是基于 ant 进行解决。

下面是具体的解决思路：

1.将jar包生成 classes2.dex 将生成的 classes2.dex 至于工程目录中的 src目录 下。
如果有多个jar包可合并成一个，可使用eclipse插件fatjar或者其它方法合并。注意,再application里面就用到的jar包,必须放在主dex即lib目录下，不然会找不到。

jar包 → dex可 参照我之前的文章 [ Android将jar包转换为dex二进制文件](http://www.jianshu.com/p/562eaf211ff5)

2.从 lib目录 下剔除用来合并的 jar 。但是剔除后为了能编译通过我们得使用外部引用：通过 bulidPath 下的 add external archives 引用被我们合并的之前的 jar。

我的做法是在src目录下新建 external-jars 文件夹,把剔除的jar包放在这里，


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-bbeb9546b9651168.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

再到本地项目目录下找到 


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-d7da2520f4d806d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

添加

```
<classpathentry kind="lib" path="external-jars/zxing.jar"/>
```



![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-95a201514548e1ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


再clean一下,项目就不会报错了。

3.导入 mutildex [分包所需要的jar包](https://github.com/chenshouyin/DevNote/tree/master/%E5%B7%A5%E5%85%B7%E6%8A%80%E5%B7%A7%E7%AF%87/eclipse%E7%AE%80%E5%8D%95%E5%88%86%E5%8C%85%E8%A7%A3%E5%86%B365535)。如图：


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-dcf10885ce7744e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


然后在自定义的 application 类中声明载入dex文件的代码MultiDex.install(this);


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-86c0f42ae14e460f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


4.至此我们调试或者打包项目的时候将不会在出现65535的问题。如果依然存在可以合并更多的jar。以此类推。



最后需要注意:

1.在 application中 使用的 jar 不能通过分包的方式，否则会提示 classnotfound。因此 application 中使用的jar还是存放于lib下。默认是主dex即 classes.dex。

2.如果打包的jar需要更新，那么得重新生成 classes 文件

3.classes 必须放置与 src根目录，并且命名规范严格按照 classes2.dex、 classes3.dex 的方式以此类推。否则会找不到不规则命名的jar包。