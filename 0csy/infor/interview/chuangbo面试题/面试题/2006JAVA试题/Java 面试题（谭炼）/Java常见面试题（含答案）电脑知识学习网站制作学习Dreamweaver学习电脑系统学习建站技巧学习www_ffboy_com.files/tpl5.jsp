<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0183)http://template.union.163.com/search/tpl5.jsp?ID=ffboycom&NO=14484&boxColor=FFFFFF&fontsize=14&decor=none&txtColor=FF0000&Key=Java常见面试题（含答案）&keyword=Java常见面试题（含答案） -->
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<SCRIPT language=javascript>
var dde_para = new Array();
var dde_aa   = new Array();
var UnionURL  = "http://union.163.com:81/pracess4.jsp";
var dde_url  = document.location.href;
var dde_pp = "";
dde_pp = dde_url.split("?")[1];
var para  = null;
var Key = "";
var keyword = "";
var ID = "163";
var NO = "0";
var fontsize = "14";
var txtColor = "0000ff";
var decor = "underline";
var boxColor = "ffffff";
var channel = 1;
if(dde_pp!=null && dde_pp.length>0){
  dde_para = dde_pp.split("&");}
  for(i=0; dde_para!=null && i<dde_para.length;i++){
  dde_aa = dde_para[i].split("=");
  eval("var "+dde_aa[0] +"='"+dde_aa[1]+"'");}
if(channel==1) searchserver = "http://cha.so.163.com/so.php";
else if(channel==2)searchserver = "http://mp3.so.163.com/mp3Search.php";
else searchserver = "http://pic.so.163.com/picSearch.php";
var tab1 = "<html><head><meta http-equiv='content-type' content=\'text/html; charset=gb2312\'></head><body bgcolor="+boxColor+"><a style='text-decoration:"+decor+";font-size:"+fontsize+"px;color:"+txtColor+";' target=_blank href='"+searchserver+"&q="+unescape(keyword)+"'>"+unescape(Key)+"</a></body></html>";
document.write(tab1);
function catchClick(){
    var dde_e = event.srcElement;
	while(dde_e && dde_e.tagName != "A"){
	if(dde_e.parentNode)
		dde_e = dde_e.parentNode;
		else return;}
    var encode_url = dde_e.href;
    if(dde_e.tagName=="A"){
	if(dde_e.href.indexOf(UnionURL)>-1) 
	return;
   dde_e.href = UnionURL+"?ID="+ID+"&NO="+NO+"&searchurl="+encode_url;}}
document.onclick=catchClick;
</SCRIPT>

<META content="MSHTML 6.00.2800.1505" name=GENERATOR></HEAD>
<BODY></BODY></HTML>
