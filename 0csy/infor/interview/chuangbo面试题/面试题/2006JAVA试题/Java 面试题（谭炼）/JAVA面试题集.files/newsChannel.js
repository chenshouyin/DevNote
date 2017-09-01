/******************************************显示新闻频道 newsChannel.js*********************************************/
/********************************* 版权所有 BLOGDRIVER  作者:怨天怨地**********************************************/
  
   /*载入RSS的XML文件*/
   function writeChannel(){
   	var bV=parseInt(navigator.appVersion);       //得到浏览器的版本
	IE4=((document.all)&&(bV>=4))?true:false;    //验证浏览器是否为IE4以上版本
   	if(IE4){
   	    if(Channel.num2==0){
   	    	document.all.Channel.innerHTML+="RSS载入中...";
   	    }
   	    Channel.startDate=new Date();            
  	    var xmlSource = new ActiveXObject("Msxml2.DOMDocument");  //创建XMLDOM对象;
  	    Channel.xmlSource=xmlSource;
  	    /*异步读取XML文件*/  	    	                     
  	    xmlSource.resolveExternals = false;                   
  	    xmlSource.onreadystatechange =Channel.createHtml; 
  	    xmlSource.load(Channel.xmlPath[Channel.num2]);	  	         	
  	}
  	else{
  	    document.write("对不起,本功能暂时不支持该浏览器");
  	}
   }
   
   /*通过XML文件生成HTML代码*/
   function createHtml(){   	      	 	 
	 var startTime=new Date();
	 var newsName;      //频道名称路径
	 var newsLike;      //频道的连接地址路径
	 var newsItem;      //频道项目的路径
	 var newsItemName;  //频道项目名称的路径
	 var newsItemLike;  //频道项目连接的路径   
    	 var str=""; 
    	 if (Channel.xmlSource.readyState == 4){
    	      var err = Channel.xmlSource.parseError;    	        	      
    	      if (err.errorCode == 0){   
    	      	if(Channel.num2==0){
    	      	   var re = /RSS载入中.../g; 
    	      	   document.all.Channel.innerHTML=document.all.Channel.innerHTML.replace(re,"");
    	      	}
    	      	/*选择RSS相应的规则*/    	      	
	    	if(Channel.rssVersion[Channel.num2]=="rss1"||Channel.rssVersion[Channel.num2]=="RSS1"){
		      newsName="/rdf:RDF/channel/title";
		      newsLike="/rdf:RDF/channel/link";
		      newsItem="/rdf:RDF/item";      
		      newsItemName="title";
		      newsItemLike="link";	      
		   }	   
		   else if(Channel.rssVersion[Channel.num2]=="rss2"||Channel.rssVersion[Channel.num2]=="RSS2"){
		      newsName="/rss/channel/title";
		      newsLike="/rss/channel/link";
		      newsItem="/rss/channel/item";      
		      newsItemName="title";
		      newsItemLike="link";
		   }
		   else if(Channel.rssVersion[Channel.num2]=="atom"||Channel.rssVersion[Channel.num2]=="ATOM"){
		      newsName="/feed/title";
		      newsLike="/feed/generator";
		      newsItem="/feed/entry";      
		      newsItemName="title";
		      newsItemLike="link";
		   }
		   else{
		      document.all.Channel.innerHTML+="无法解析该RSS的格式";
		      return;
		   }
		   /*得到频道名称和连接地址*/
		   var node=Channel.xmlSource.selectSingleNode(newsName);  
		   var blogName="";
		   var blogLike="";	
		   if(node!=null){
		      var blogName=node.text;
		   }
		   node=Channel.xmlSource.selectSingleNode(newsLike);   
		   if(node!=null){
		      var blogLike=node.text;
		   }
		   if(blogName!=""&&blogLike!=""){
		      str+="<DIV class='rss'><span class='rssTitle'>"+blogName+"</span><ul>";
		   }
		   /*得到指定个数的新闻项的名称和连接地址*/
		   var nodes=Channel.xmlSource.selectNodes(newsItem);
		   var j=0;	        
		   for(i=0;i<nodes.length;i++){	     
		   	var ItemName;
		   	var ItemLike;
		   	if(Channel.rssVersion=="atom"){
		   	    ItemName=nodes.item(i).getElementsByTagName(newsItemName).item(0).text
		   	    ItemLike=nodes.item(i).getElementsByTagName(newsItemLike).item(0).getAttribute("href");
		   	}
		   	else{
		   	   ItemName=nodes.item(i).getElementsByTagName(newsItemName).item(0).text;
		   	   ItemLike=nodes.item(i).getElementsByTagName(newsItemLike).item(0).text;
		   	}
		   	str+="<li class='newsItem'><a href="+ItemLike+" target='_blank'>"+ItemName+"</a></li>";	   
		   	if(j==Channel.count[Channel.num2]){
		   		break;
		   	}
		   	j++;
		   }
		   if(blogName!=""&&blogLike!=""){	   
		        var endTime=new Date();	   
		   	str+="</ul><span class='loadTime'>载入时间:"+(endTime-Channel.startDate)/1000+"秒</span></DIV>";	   
		   }
		   document.all.Channel.innerHTML+=str;
		   Channel.num2++;
		   if(Channel.num2<Channel.num){
		      writeChannel();
		   }
		   else{
		       Channel.xmlPath=null;
		       Channel.count=null;
		       Channel.rssVersion=null;
		       Channel.xmlSource=null;
		       Channel.num=0;		       
   		       Channel.num2=0;  
		   }
	    }
	}
    }    
        
   /*新闻频道对象构造函数*/
   function Channel(xmlPath,count,rssVersion){   	
   	Channel.xmlPath[Channel.num]=xmlPath;
   	Channel.count[Channel.num]=count;   	
   	Channel.rssVersion[Channel.num]=rssVersion;
    	Channel.num++;
   }
      
   Channel.xmlPath=new Array();    // 保存XML地址的数组
   Channel.count=new Array();      // 保存需要显示新闻项目个数的数组
   Channel.rssVersion=new Array(); // 保存使用何种RSS的标识的数组
   Channel.xmlSource;              // XMLDOM对象
   Channel.startDate;              // 开始工作时间
   Channel.num=0;                  // 频道总个数
   Channel.num2=0;                 // 当前生成的频道数
   Channel.writeChannel=writeChannel; 
   Channel.createHtml=createHtml;
   
   
   
   