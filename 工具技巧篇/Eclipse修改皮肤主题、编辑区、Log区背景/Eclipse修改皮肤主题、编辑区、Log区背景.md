

修改前


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-cf53508d5af987d7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


修改后


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-2f42b0d00baa1977.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


怎样改变eclipse的皮肤呢？其实很简单，国外程序员自己打造的Eclipse黑色主题皮肤，配合eclipse color theme插件使用黑色代码主题就可以了。


首先从 [Eclipse修改皮肤主题插件](https://github.com/chenshouyin/DevNote/tree/master/%E5%B7%A5%E5%85%B7%E6%8A%80%E5%B7%A7%E7%AF%87/Eclipse%E4%BF%AE%E6%94%B9%E7%9A%AE%E8%82%A4%E4%B8%BB%E9%A2%98%E3%80%81%E7%BC%96%E8%BE%91%E5%8C%BA%E3%80%81Log%E5%8C%BA%E8%83%8C%E6%99%AF) 下载主题，解压下载的压缩文件，将plugins文件夹放入eclipse目录的dropins文件夹下，重启eclipse后，选择Preferences->General->Appearance，选择dark主题即可。


选择之后设这样的

![%@8YI_P(6AMO(TCMGH{YY7L.jpg](http://upload-images.jianshu.io/upload_images/2704327-04107efb075fa66d.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

接下来还要修改编辑区域背景，修改文本编辑的背景啥的就需要安装Eclipse Color Theme了，这个也很简单：
 Help->Install New Software ->add->name(输入Color Theme) Location:http://eclipse-color-theme.github.com/update点击ok之后，出现这样的画面：
![](http://upload-images.jianshu.io/upload_images/2704327-f515fb9b124a948c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
选中之后一直下一步下一步，最后Eclipse会重启一次。重启之后，Windows->Perferences->General->Appearence->Color Theme这么多皮肤，可以随便挑。

选择Cokor Theme之后是这样的:


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-c52c793fb7989ec8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


可以看到输入区的背景颜色更改了，但是，控制台，Logcat等背景颜色并没有更改。还是亮闪闪的白色。


因为更改这些区域颜色的方法并不在Eclipse里！

1.右键点击桌面→个性化

2.点击下方 "窗口颜色"


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-156f84f5eb1eaad4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


3.下方高级外观设置


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-8891f8f34e6b410e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


4.中间项目 的下拉列表里选"窗口"


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-8b4c6daf207522c5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


在此可以更改窗口的背景颜色和文字颜色。自己根据字体颜色搭配背景。

点右边窗口颜色设置，颜色里选"其他"配置自定义颜色按84,122,203即可。或者更改成其它颜色。

但是注意下哦,更改了窗口颜色和文字颜色之后也会影响电脑里面的文件夹名字显示。


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-904d3007703310fe.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)






