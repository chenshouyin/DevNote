function getCookieVal(offset)
{
 var iEndStr=document.cookie.indexOf(";",offset);
 if(iEndStr==-1)
 iEndStr=document.cookie.length;
 return unescape(document.cookie.substring(offset,iEndStr));
}

function getCookie(name)
{
 var strArg=name+"=";
 var iArgLength=strArg.length;
 var iCookieLength=document.cookie.length;
 var iIndex=0;
 while(iIndex<iCookieLength)
 {
  var kIndex=iIndex+iArgLength;
  if(document.cookie.substring(iIndex,kIndex)==strArg)
   return getCookieVal(kIndex);
  iIndex=document.cookie.indexOf(" ",iIndex)+1;
  if(iIndex==0)
  break;
 }
 return null;
}

function reply(){
document.getElementById('replyForm').action="http://publishblog.blogchina.com/blog/postRemark.b";

if(document.getElementById('replyForm').remark.value.length > 1000){
alert("评论字数太多，不能发布！");
document.getElementById('replyForm').remark.focus();
return false;
}
if(document.getElementById('replyForm').remark.value.length==''){
alert("评论不能为空！");
document.getElementById('replyForm').remark.focus();
return false;
}
document.getElementById('replyForm').submit();
}