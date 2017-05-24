
##一、什么是SourceTree?

SourceTree 是 Windows 和Mac OS X 下免费的 Git 和 Hg 客户端，拥有可视化界面，容易上手操作。同时它也是Mercurial和Subversion版本控制系统工具。支持创建、提交、clone、push、pull 和merge等操作。简单点说SourceTree是跨Windows和Mac平台的一款版本控制工具。


##二、如何使用SourceTree,本篇主要将在Mac的使用

首先去官网下载客户端 https://www.sourcetreeapp.com/ 下载Mac版的客户端，如果是Windows系统下载Widows版本的客户端，下载之后安装即可。


##三、如何进行版本控制?
 
远程仓库以Github为例（当然实际项目中可能是公司服务器中的一个仓库）,首先需要注册一个Github账号,如果还没注册，可以去官网注册 https://github.com/ （实际项目中公司都会给我们仓库对应的用户名密码）,注册完之后登陆创建一个仓库。

可通过如下方式创建仓库

方法1：
在欢迎页点击“+ New repository“创建我们的仓库
![](http://upload-images.jianshu.io/upload_images/2704327-93f1380f6e4648d7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

方法2
或点击右上角的“+”，然后再New repository亦可
![](http://upload-images.jianshu.io/upload_images/2704327-5159c0b4d866b03d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

按照个人需要填写仓库名、仓库描述等，建议勾选“Initialize this repository with a README”（注意此处免费账户只能选择建立public（开源）仓库），填写完成后点击Create repository
![](http://upload-images.jianshu.io/upload_images/2704327-0d483d9c2f54eabc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)Create Repository 3

至此，我们的仓库已经创建成功。创建成功后，我们在页面的右下角找到链接，点击复制
![](http://upload-images.jianshu.io/upload_images/2704327-2d0210a05418e60d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

打开我们的SourceTree，点击：“+新仓库”，选择：“从URL克隆”
![](http://upload-images.jianshu.io/upload_images/674642-c66863c9567a926a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

粘贴我们的仓库链接至源URL，SourceTree会自动帮我们生成目标路径（本地仓库路径）以及名称，点击克隆
![](http://upload-images.jianshu.io/upload_images/674642-0e1e5ddeceb27970.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)Clone 2

等待数秒后，SourceTree会为我们自动打开我们刚才克隆的仓库，选择master选项，这里我们可以看到我们仓库里的所有文件
![](http://upload-images.jianshu.io/upload_images/2704327-f271ef701f4baf93.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

接下来我们想要上传一个项目至我们的远程Github仓库内。我们点击右上角“在Finder”中显示。然后SourceTree会帮我们打开我们的本地仓库，我们将需要上传的项目复制到本地的Finder文件夹内，然后关闭文件夹，回到主页面。我们会发现工作副本出现了更改提示
![](http://upload-images.jianshu.io/upload_images/2704327-b65673be19a6aa7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我们点击工作副本，然后我们发现我们刚才上传的文件都在未暂存文件当中，此时，我们勾选“未暂存文件”
![](http://upload-images.jianshu.io/upload_images/2704327-1003c05022407fbd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

发现我们的文件变成了已暂存文件。此时，我们可以输入更新信息，然后，点击提交按钮
![](http://upload-images.jianshu.io/upload_images/2704327-3067c461d9a71459.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

 我们切换回master分支，会发现master分支以及推送（Push）按钮，都出现了更改提示。这表示SourceTree已经将我们刚才添加的文件成功提交到本地仓库，而本地仓库的内容则比远程仓库超前了一个版本。我们这个时候点击推送（Push）即可将本地仓库的内容同步至远程仓库。
![](http://upload-images.jianshu.io/upload_images/2704327-01bc35f806c04933.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

点击推送（push），等待片刻即可，我们重新登录github网站，会发现我们刚才本地仓库的文件已经成功推送到远程仓库
![](http://upload-images.jianshu.io/upload_images/2704327-be399a0dded63c91.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)