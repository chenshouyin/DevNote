function ObjectAD() {
  /* Define Variables*/
  this.ADID        = 0;
  this.ADType      = 0;
  this.ADName      = "";
  this.ImgUrl      = "";
  this.ImgWidth    = 0;
  this.ImgHeight   = 0;
  this.FlashWmode  = 0;
  this.LinkUrl     = "";
  this.LinkTarget  = 0;
  this.LinkAlt     = "";
  this.Priority    = 0;
  this.CountView   = 0;
  this.CountClick  = 0;
  this.InstallDir  = "";
}

function BannerZoneAD(_id) {
  /* Define Constants */
  this.adNum       = 0;
  this.adDelay     = 6000;

  /* Define Common Variables*/
  this.ID          = _id;
  this.ZoneID      = 0;
  this.ZoneName    = "";
  this.ZoneWidth   = 0;
  this.ZoneHeight  = 0;
  this.ShowType    = 1;
  this.DivName     = "";
  this.Div         = null;

  /* Define Unique Variables*/

  /* Define Objects */
  this.AllAD       = new Array();
  this.ShowAD      = null;

  /* Define Functions */
  this.AddAD       = BannerZoneAD_AddAD;
  this.GetShowAD   = BannerZoneAD_GetShowAD;
  this.Show        = BannerZoneAD_Show;
  this.LoopShow    = BannerZoneAD_LoopShow;

}

function BannerZoneAD_AddAD(_AD) {
  this.AllAD[this.AllAD.length] = _AD;
}

function BannerZoneAD_GetShowAD() {
  if (this.ShowType > 1) {
    this.ShowAD = this.AllAD[0];
    return;
  }
  var num = this.AllAD.length;
  var sum = 0;
  for (var i = 0; i < num; i++) {
    sum = sum + this.AllAD[i].Priority;
  }
  if (sum <= 0) {return ;}
  var rndNum = Math.random() * sum;
  i = 0;
  j = 0;
  while (true) {
    j = j + this.AllAD[i].Priority;
    if (j >= rndNum) {break;}
    i++;
  }
  this.ShowAD = this.AllAD[i];
}

function BannerZoneAD_Show() {
  if (!this.AllAD) {
    return;
  } else {
    this.GetShowAD();
  }

  if (this.ShowAD == null) return false;
  this.DivName = "BannerZoneAD_Div" + this.ZoneID;
  if (this.ShowAD.ImgWidth) this.ShowAD.ImgWidth = this.ZoneWidth
  if (this.ShowAD.ImgHeight) this.ShowAD.ImgHeight = this.ZoneHeight
  if (this.ShowType == 3) {
    document.write("<div id='" + this.DivName + "' style='visibility:visible; z-index:1; width:" + this.ZoneWidth + "; height:" + this.ZoneHeight + "; filter: revealTrans(duration=2,transition=20);'>" + AD_Content(this.ShowAD) + "</div>");
  } else {
    if (navigator.appName == "Netscape") {
      document.write("<layer id='" + this.DivName + "' width='" + this.ZoneWidth + "' height='" + this.ZoneHeight + "'>" + AD_Content(this.ShowAD) + "</layer>");
    } else {
      document.write("<div id='" + this.DivName + "' style='visibility:visible; z-index:1; width:" + this.ZoneWidth + "; height:" + this.ZoneHeight + ";'>" + AD_Content(this.ShowAD) + "</div>");
    }
    if (this.ShowAD.CountView) {
      document.write ("<script src='" + this.ShowAD.InstallDir + "AD/ADCount.asp?Action=View&ADID=" + this.ShowAD.ADID + "'></" + "script>")
    }
  }
  this.Div = document.getElementById(this.DivName);
  if (this.ShowType == 3) this.LoopShow();
}

function BannerZoneAD_LoopShow() {
  if(this.adNum<this.AllAD.length-1) this.adNum++ ; 
  else this.adNum=0; 
  this.Div.filters.revealTrans.Transition=Math.floor(Math.random()*23); 
  this.Div.filters.revealTrans.apply(); 
  if (this.AllAD[this.adNum].ImgWidth) this.AllAD[this.adNum].ImgWidth = this.ZoneWidth
  if (this.AllAD[this.adNum].ImgHeight) this.AllAD[this.adNum].ImgHeight = this.ZoneHeight
  this.Div.innerHTML=AD_Content(this.AllAD[this.adNum]);
  this.Div.filters.revealTrans.play() 
  this.Div.timer=setTimeout(this.ID+".LoopShow()",this.adDelay);
}

function AD_Content(o) {
  var str = "";
  if (o.ADType == 1 || o.ADType == 2) {
    o.ImgUrl = o.ImgUrl.toLowerCase()
    if (o.ImgUrl.indexOf(".swf") !=  - 1) {
      str = "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0'";
      str += " name='AD_" + o.ADID + "' id='AD_" + o.ADID + "'";
      str += " width='" + o.ImgWidth + "'";
      str += " height='" + o.ImgHeight + "'";
      if (o.style) str += " style='" + o.style + "'";
      if (o.extfunc) str += " " + o.extfunc + " ";
      str += ">";
      str += "<param name='movie' value='" + o.ImgUrl + "'>";
      if (o.FlashWmode == 1) str += "<param name='wmode' value='Transparent'>";
      if (o.play) str += "<param name='play' value='" + o.play + "'>";
      if (typeof(o.loop) != "undefined") str += "<param name='loop' value='" + o.loop + "'>";
      str += "<param name='quality' value='autohigh'></object>";
      str = "<embed ";
      str += " name='AD_" + o.ADID + "' id='AD_" + o.ADID + "'";
      str += " width='" + o.ImgWidth + "'";
      str += " height='" + o.ImgHeight + "'";
      if (o.style) str += " style='" + o.style + "'";
      if (o.extfunc) str += " " + o.extfunc + " ";
      str += " src='" + o.ImgUrl + "'";
      if (o.FlashWmode == 1) str += " wmode='Transparent'";
      if (o.play) str += " play='" + o.play + "'";
      if (typeof(o.loop) != "undefined") str += " loop='" + o.loop + "'";
      str += " quality='autohigh'"
      str += " pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' type='application/x-shockwave-flash'></embed>";
      str += "</object>";
    } else if (o.ImgUrl.indexOf(".gif") !=  - 1 || o.ImgUrl.indexOf(".jpg") !=  - 1 || o.ImgUrl.indexOf(".jpeg") !=  - 1 || o.ImgUrl.indexOf(".bmp") !=  - 1 || o.ImgUrl.indexOf(".png") !=  - 1) {
      if (o.LinkUrl) {
        if (o.CountClick) o.LinkUrl = o.InstallDir + "AD/ADCount.asp?Action=Click&ADID=" + o.ADID
        str += "<a href='" + o.LinkUrl + "' target='" + ((o.LinkTarget == 0) ? "_self" : "_blank") + "' title='" + o.LinkAlt + "'>";
      }
      str += "<img ";
      str += " name='AD_" + o.ADID + "' id='AD_" + o.ADID + "'";
      if (o.style) str += " style='" + o.style + "'";
      if (o.extfunc) str += " " + o.extfunc + " ";
      str += " src='" + o.ImgUrl + "'";
      if (o.ImgWidth) str += " width='" + o.ImgWidth + "'";
      if (o.ImgHeight) str += " height='" + o.ImgHeight + "'";
      str += " border='0'>";
      if (o.LinkUrl) str += "</a>";
    }
  } else if (o.ADType == 3 || o.ADType == 4) {
    str = o.ADIntro
  } else if (o.ADType == 5) {
    str = "<iframe id='" + "AD_" + o.ADID + "' marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=no width=100% height=100% src='" + o.ADIntro + "'>wait</iframe>";
  }
  return str;
}


var ZoneAD_2 = new BannerZoneAD("ZoneAD_2");
ZoneAD_2.ZoneID      = 2;
ZoneAD_2.ZoneWidth   = 728;
ZoneAD_2.ZoneHeight  = 90;
ZoneAD_2.ShowType    = 1;

var objAD = new ObjectAD();
objAD.ADID           = 3;
objAD.ADType         = 4;
objAD.ADName         = "网站通用760广告，上页的";
objAD.ImgUrl         = "";
objAD.ImgWidth       = 0;
objAD.ImgHeight      = 0;
objAD.FlashWmode     = 0;
objAD.ADIntro        = "<iframe border=0 align=center vspace=0 hspace=0 name=searchbar marginwidth=0 marginheight=0 framespacing=0 frameborder=0 scrolling=no width=750 height=14 src=\"http://template.union.163.com/search/tpl6.jsp?ID=ffboycom&NO=14484&sessionID=2274bo2843452000fb19ffff&codeDate=2005n8y2h&logo=1&select=1&SearchHeight=14&SearchWidth=758&boxColor=ffffff&txtColor=400040&borderColor=333333&Key=%u8D85%u7EA7%u5973%u58F0:%u6C34%u6D52%u56FD%u9A82:%u7206%u7B11%u6807%u8BED:%u611F%u52A8%u810F%u8BDD:%u7231%u60C5%u5974%u96B6:%u661F%u7237%u65B0%u5BA0:%u88F8%u4F53%u4E5E%u8BA8:%u9676%u5B50%u7ED3%u5A5A:%u7537%u5973%u5C34%u5C2C:%u6700%u5F3A%u7537%u4EBA:%u6881%u795D%u540C%u5C45:%u63ED%u5BC6%u7B14%u4ED9:%u5168%u88F8%u66F2%u7EBF:%u7FA4%u4F53%u88F8%u6CF3:%u5927%u6D77%u7F8E%u5973:%u5BBF%u820D%u602A%u4E8B:%u81EA%u5236%u88F8%u4F53:%u6700%u6BD2%u5206%u624B:%u8D75%u8587%u59B9%u59B9:%u5047%u671F%u7537%u53CB:%u5FC3%u8BA1%u7F8E%u5973:%u5904%u5973%u516C%u8BC1:%u65E0%u803B%u8BED%u5F55:%u8D85%u7EA7%u5973%u58F0:%u6700%u7231%u63A5%u543B:%u660E%u661F%u79D8%u4E8B:%u6050%u6016%u8273%u9047:%u60C5%u4FA3%u72C2%u543B:%u6709%u59BB%u5F92%u5211:%u7A77%u5149%u86CB%u6392%u884C:%u8EAB%u4EFD%u8BC1%u66DD%u5149:%u641E%u7B11%u6BD5%u4E1A%u7167:%u5468%u6E1D%u6C11%u8F66%u7978:%u5929%u4E0B%u7B2C%u4E00%u9A82:%u7F8E%u5973%u8BBA%u65A4%u5356:%u91D1%u5EB8%u7248%u5973%u58F0:%u5218%u4EA6%u83F2%u58F0%u660E:%u543B%u7834%u8033%u819C:%u5BDD%u5BA4%u6697%u53F7:%u7EC3%u4E60%u63A5%u543B:%u8D75%u8587%u6FC0%u53D8:%u7559%u7F8E%u65E5%u8BB0:%u5904%u5973%u514D%u8D39:%u5927S%u65B0%u604B%u60C5:%u5A36%u4E8C%u623F%u7533%u8BF7:%u8FFD%u5973%u5B69%u5EFA%u8BAE:%u514D%u8D39%u5A36%u8001%u5A46:%u7ECF%u5178%u79C1%u623F%u8BDD:%u6881%u671D%u4F1F%u7ED3%u5A5A:%u4E0E%u8001%u5916%u540C%u5C45:%u4E0E%u7EE7%u6BCD%u79C1%u901A:%u8D85%u77ED%u88D9%u4E8B%u4EF6:%u8868%u6F14%u88F8%u4F53%u79C0&qword=美女\"></iframe>  <iframe WIDTH=750 HEIGHT=70 align=center MARGINWIDTH=0 Name=union163com MARGINHEIGHT=0 HSPACE=0 VSPACE=0 FRAMEBORDER=0 SCROLLING=no src=\"http://news2.163.com/product/photo/tpl2.jsp?ID=ffboycom&NO=14484&codeDate=2005n6y30h&productid=0422\"></iframe>  <script  src=\"http://host.ads99.net/banner760.php\"userid=anyangaaa\"></script>";
objAD.LinkUrl        = "";
objAD.LinkTarget     = 1;
objAD.LinkAlt        = "";
objAD.Priority       = 1;
objAD.CountView      = 0;
objAD.CountClick     = 0;
objAD.InstallDir     = "/";
ZoneAD_2.AddAD(objAD);

ZoneAD_2.Show();
