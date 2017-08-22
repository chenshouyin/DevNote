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


var ZoneAD_6 = new BannerZoneAD("ZoneAD_6");
ZoneAD_6.ZoneID      = 6;
ZoneAD_6.ZoneWidth   = 728;
ZoneAD_6.ZoneHeight  = 90;
ZoneAD_6.ShowType    = 1;

var objAD = new ObjectAD();
objAD.ADID           = 5;
objAD.ADType         = 4;
objAD.ADName         = "文章下面广告760";
objAD.ImgUrl         = "";
objAD.ImgWidth       = 0;
objAD.ImgHeight      = 0;
objAD.FlashWmode     = 0;
objAD.ADIntro        = "<iframe marginwidth=0 marginheight=0  frameborder=0 bordercolor=\'#000000\' scrolling=no src=\'http://partner.p4p.tom.com/cpc/league.php?pid=ffboycom&method=text&style=1\' width=760 height=84></iframe>\n\r<script>\n\red_ads_UserId=10472;\n\r</script>\n\r";
objAD.LinkUrl        = "";
objAD.LinkTarget     = 1;
objAD.LinkAlt        = "";
objAD.Priority       = 1;
objAD.CountView      = 0;
objAD.CountClick     = 0;
objAD.InstallDir     = "/";
ZoneAD_6.AddAD(objAD);

ZoneAD_6.Show();
