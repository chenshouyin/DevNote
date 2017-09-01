var countid=33394847;var logo=1;


function check_array(searchstring,searcharray) 
   {          
    searchstring = searchstring.toLowerCase();
    for (var i = 1; i < searcharray.length; i++) 
      {   
       offset = searchstring.indexOf(searcharray[i]);
       if(offset != -1) return i;   
      }
    return 999;
   }

function etonone(eworkstring,eescapestring) 
   {
	var eki=0;
	var eksplits="";
    var ekwords=eworkstring.split(eescapestring);
    for (eki=0; eki<ekwords.length; eki++) 
	   {
		eksplits = eksplits + ekwords[eki] ;
	   }
	return eksplits;
   }

function refesimple(refestring) 
   {          
    var prefix='http://';
    refestring = refestring.toLowerCase();
    j = refestring.indexOf('http://');
    if(j == -1) 
      {
       j = refestring.indexOf('https://');
       if(j != -1) j = j + 1;
       prefix='https://';
      } 
    if(j == -1) 
      {
       return refestring;
      }
    else
      {
       subrefestring = refestring.substring(j+7,refestring.length);
       offset = subrefestring.indexOf('/');
       if(offset != -1) subrefestring = subrefestring.substring(0, offset);
       return prefix + subrefestring;
      }  
   }

function locationsimple(locationstring) 
   {          
    locationstring = locationstring.toLowerCase();
	offset = locationstring.indexOf('%3f');
    if(offset != -1) 
	   {
	    sublocationstring = locationstring.substring(0, offset);
        return sublocationstring;
	   }
	else
	   {
	    return locationstring;
	   }
   }


function parse_keyword(searchstring,wordarray,paraarray) 
   {          
    var j=0,k=0;
	for (var i = 1; i < wordarray.length; i++)
      {
       offset = searchstring.indexOf(wordarray[i]);
       if(offset != -1) j = j + 1;
      }
	if(j==0) return '';
	
	sarray = new Array(j);
	for (i = 1; i < wordarray.length; i++)
      {
       offset = searchstring.indexOf(wordarray[i]);
       if(offset!=-1)
	     {
		  sarray[k]=i;
		  k=k+1;
		 }
      }
	for ( i = 0; i < sarray.length; i++)
      {
       offset = searchstring.indexOf(paraarray[sarray[i]]);
	   if(offset != -1)
	     {
  	      subsearchstring = searchstring.substring(offset,searchstring.length);
		  offset = subsearchstring.indexOf('&');
	      if(offset != -1)
            {
             return subsearchstring.substring(paraarray[sarray[i]].length,offset);
            }
          else
     	    {
    	     return subsearchstring.substring(paraarray[sarray[i]].length,subsearchstring.length); 
     	    }
		 }
      }

	 return '';
   }



function yesgetCookieVal(offset) {
	var endstr=document.cookie.indexOf(";",offset);
	if( endstr==-1 )
		endstr=document.cookie.length;
	return document.cookie.substring(offset,endstr);
}

function yesGetCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	var j;
	while( i<clen ) {
		j = i + alen;
		if( document.cookie.substring(i,j)==arg )
			return yesgetCookieVal(j);
		i = document.cookie.indexOf(" ",i)+1;
		if(i==0)
			break;
	}
	return null;
}


function ccklasttime() 
{
	var yesexpireDate = new Date();
	var yesexpiretime = 93312000;
	var yesvisitor = 1000 * 36000;
	var yesctime = yesexpireDate.getTime();
	yesexpireDate.setTime (yesexpireDate.getTime() + yesexpiretime);

    var yesoffset = document.cookie.indexOf("cck_lasttime");
    if (yesoffset == -1) 
    {
	  document.cookie = "cck_lasttime=" + yesctime + "; expires=" + yesexpireDate.toGMTString() +  "; path=/";
	  document.cookie = "cck_count=0; expires=" + yesexpireDate.toGMTString() +  "; path=/";
	  return 0;
	}
	else
	{
	  var yescookie1 = yesGetCookie("cck_lasttime");
	  var yescookie2 = yesGetCookie("cck_count");
	  yescookie1=parseInt(yescookie1);
	  yescookie2=parseInt(yescookie2);
	  yescookie3=yesctime - yescookie1 ;
	  if(yescookie3>yesvisitor) 
	    {
		 yescookie2=yescookie2+1;
   	     document.cookie = "cck_lasttime=" + yesctime + "; expires=" + yesexpireDate.toGMTString() +  "; path=/";
		 document.cookie = "cck_count=" + yescookie2 + "; expires=" + yesexpireDate.toGMTString() +  "; path=/";
		}
	  return yescookie2;
	}
}




os = new Array("","windows nt 5.2","windows nt 5.1","windows nt 5.0","TBD","windows 98","windows 95","windows nt 4.0","windows ce","linux","mac","pda","symbian os");
browser = new Array("","msie 6.","msie 5.","msie 4.","netscape","firefox","opera");
color = new Array("","32x","24x","16x","8x");
resolution = new Array("","1600x1200","1280x1024","1152x864","1024x768","800x600","640x480");
languagearray = new Array("","zh-cn","zh-hk","zh-mo","zh-sg","zh-tw","en");
searchengin = new Array("","google.com","baidu.com","3721.com","yisou.com","sogou.com","sina.com","search.yahoo.com","search.tom.com","so.163.com",".qq.com","search.msn.com");
searchenginword = new Array("","google.com","baidu.com","baidu.com","3721.com","3721.com","yisou.com","sogou.com","sina.com","sina.com","search.yahoo.com","search.tom.com","search.tom.com","so.163.com",".qq.com","search.msn.com");
searchenginpara = new Array("","q=","wd=","word=","p=","name=","p=","query=","searchkey=","word=","p=","word=","w=","q=","word=","q=");
alexaarray = new Array("","alexa");


var yeadata;

cbrowser = check_array(navigator.userAgent,browser);
if(cbrowser>=1 && cbrowser<=3)
  {
   language = check_array(navigator.systemLanguage,languagearray);
  }
else
  {
   language = 2; 
  }
if(language>=2 && language<=5) language=2;
if(language==6) language=3;

var khaa = document.referrer;

khof = khaa.indexOf('GB2312');
if(khof != -1) 
  {
   khgb = 1;
  }
else
  {
   khgb = 0;
   khof = khaa.indexOf('ei=gb');
   if(khof != -1) khgb = 1;
  }



yesdata = '&refe=' + escape(khaa) + '&refesimple=' + escape(refesimple(khaa)) + '&location=' + escape(document.location) + '&locationsimple=' + locationsimple(escape(document.location)) + '&os=' + check_array(navigator.userAgent,os) + '&browser=' + check_array(navigator.userAgent,browser) + '&color=' + check_array(screen.colorDepth+'x',color) + '&resolution=' + check_array(screen.width+'x'+screen.height,resolution) + '&language=' + language + '&searchengin=' + check_array(khaa,searchengin) + '&keyword=' + parse_keyword(khaa,searchenginword,searchenginpara) + '&gb=' + khgb + '&alexa=' + check_array(navigator.userAgent,alexaarray) + '&returning=' + ccklasttime();
yesdata =  etonone(yesdata,"'");
yesdata =  etonone(yesdata,"\\");
yesdata =  etonone(yesdata,"%27");
yesdata =  etonone(yesdata,"%5C");
yesdata =  etonone(yesdata,"%5c");

if(logo!=12)
{
document.write('<a href="http://count.51yes.com/index.aspx?id='+ countid +'" target=_blank>');
document.write('<img width=20 height=20 border=0 hspace=0 vspace=0 src="http://count3.51yes.com/count'+ logo +'.gif" alt="51YES网站统计系统"></a>');
}
else
{
document.write('<a href="http://count.51yes.com/index.aspx?id='+ countid +'" target=_blank title="51YES网站统计系统">流量统计</a>');
}
document.write('<iframe MARGINWIDTH=0 MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=no src=http://count3.51yes.com/saveclick.aspx?id='+ countid + yesdata+ ' height=0 width=0></iframe>');


