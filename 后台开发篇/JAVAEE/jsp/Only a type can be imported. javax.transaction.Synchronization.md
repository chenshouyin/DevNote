Only a type can be imported. javax.transaction.Synchronization

```
Type Exception Report
Message Unable to compile class for JSP:
Description The server encountered an unexpected condition that prevented it from fulfilling the request.
Exception
org.apache.jasper.JasperException: Unable to compile class for JSP: 

An error occurred at line: [14] in the generated java file: [D:\Tomcat\apache-tomcat-9.0.0.M21\work\Catalina\localhost\JspProject\org\apache\jsp\define_jsp.java]
Only a type can be imported. javax.transaction.Synchronization resolves to a package
```

大致意思是只能导入一种类型,而不是一种一个包。

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/2704327-7a43e324190b02c4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


解决办法是
把

```
javax.transaction.Synchronization
```

改成

```
javax.transaction.*
```

即可。



[我的GitHub](http://blog.csdn.net/e_inch_photo)
[我的CSDN](https://github.com/chenshouyin?tab=repositories)
[我的简书](http://www.jianshu.com/u/303ec9abdc08)
[开发笔记](https://github.com/chenshouyin/DevNote)
