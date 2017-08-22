var host="http://redirect.union.163.com/";
function Element(url,imgsrc,redirectUrl){
	this.url=url;
	this.imgsrc=imgsrc;
	this.redirectUrl=redirectUrl;
}
var elements=new Array();
elements[elements.length]=new Element("http://photo.163.com/","images/p1/","process_product2.jsp");
elements[elements.length]=new Element("http://www.photo.163.com/compete/ipn/index.htm","images/p2/","process_product2.jsp");
var theTimer;
var j=elements.length-1;
var current=-1;
function nextAd(){	
	if(current<j){
		current++;
	}
	else{
		current=0;
	}
	var imgInit=document.getElementById("imgInit");
	if (imgInit){
		if(imgInit.filters){			
			imgInit.filters[0].Transition=17;
			imgInit.filters[0].apply();
		}
	}
	imgInit.src=elements[current].imgsrc+prefix;
	goUrl();
	if(imgInit.filters){
		imgInit.filters[0].play();
	}
	theTimer=setTimeout("nextAd()", 6000);

}
function init(){
	current= Math.floor(Math.random()*elements.length);
	var imgInit=document.getElementById("imgInit");
	imgInit.src=elements[current].imgsrc+prefix;
	goUrl();
	theTimer=setTimeout("nextAd()", 6000);
}
window.onload=init;
function goUrl(){
	var href=host+elements[current].redirectUrl+"?ID="+ID+"&NO="+NO+"&url="+elements[current].url;	
	document.getElementById("hideA").href=href;
}
var dde_para = new Array();
var dde_aa   = new Array();
var dde_url  = document.location.href;
var dde_pp   = dde_url.split("?")[1];
var para     = null;
var UnionURL  = "http://redirect.union.163.com/process_product2.jsp";
var ID = "163";
var NO = "0";
if(dde_pp!=null && dde_pp.length>0){
  dde_para = dde_pp.split("&");}
  for(i=0; dde_para!=null && i<dde_para.length;i++){
  dde_aa = dde_para[i].split("=");
  eval("var "+dde_aa[0] +"='"+dde_aa[1]+"'");}

