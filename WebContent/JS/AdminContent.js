var ajaxobj1=new XMLHttpRequest();
var ajaxobj2=new XMLHttpRequest();
var ajaxdtls=new XMLHttpRequest();
var ajaxthb=new XMLHttpRequest();
var ajaxcathome=new XMLHttpRequest();
var ajaxcatedit=new XMLHttpRequest();
var context;
function picname1() {
	var cat=document.getElementById("selectcat1");
	ajaxobj1.open("GET","PicnameReturn?cat="+cat.value+"",true);
	ajaxobj1.onreadystatechange=function() {
		if(ajaxobj1.readyState===4 && ajaxobj1.status===200)
			document.getElementById("selectpic1").innerHTML=ajaxobj1.responseText;
		forthumb('selectpic1');
	};
	ajaxobj1.send(null);
}

function picname2() {
	var cat=document.getElementById("selectcat2");
	ajaxobj2.open("GET","PicnameReturn?cat="+cat.value+"",true);
	ajaxobj2.onreadystatechange=function() {
		if(ajaxobj2.readyState===4 && ajaxobj2.status===200) {
			document.getElementById("selectpic2").innerHTML=ajaxobj2.responseText;
		    details();
		    }
	};
	ajaxobj2.send(null);
	document.getElementById("selectcat3").selectedIndex=document.getElementById("selectcat2").selectedIndex;
}

function details() {
	var cat=document.getElementById("selectcat2");
	var pic=document.getElementById("selectpic2");
ajaxdtls.open("GET","PicdetailReturn?cat="+cat.value+"&pic="+pic.value+"",true);
	ajaxdtls.onreadystatechange= function () {
		if(ajaxdtls.readyState===4 && ajaxdtls.status===200 ) {
			var pn;
			if((pn=ajaxdtls.responseXML.getElementsByTagName("PC")[0].firstChild)===null) document.getElementById("cptn").value="";
			else document.getElementById("cptn").value=pn.nodeValue;
			forthumb('selectpic2');
	   }
	};
	ajaxdtls.send(null);	
}

function forthumb(ele) {
	var picname=document.getElementById(ele);
	ajaxthb.open("GET","ThumbReturn?pic="+picname.value+"",true);
	ajaxthb.onreadystatechange=function() {
		if(ajaxthb.readyState===4 && ajaxthb.status===200 ) {
			var resp=ajaxthb.responseText+".JPG";
			if(ele=="selectpic2") cheditthumb(resp);
			else if(ele=="selectpic1") chdeletethumb(resp);
		}
	};
	ajaxthb.send(null);
}

function cheditthumb(thumb) {
	setthumb("edit","Thumbs/"+thumb);
}
function chdeletethumb(thumb) {
	setthumb("delete","Thumbs/"+thumb);
}
function setthumb(thumbno,thumbsrc)
{
	document.getElementById(thumbno).src=thumbsrc;
	document.getElementById(thumbno).onload=function()
	{
		var thumbwidth = document.getElementById(thumbno).naturalWidth;
		var thumbheight = document.getElementById(thumbno).naturalHeight;
		if(thumbwidth/thumbheight<=3/2)
		{
			document.getElementById(thumbno).style.height="100px";
			document.getElementById(thumbno).style.width="auto";
			document.getElementById(thumbno).style.top="0px";
		}
		else
		{
			document.getElementById(thumbno).style.height="auto";
			document.getElementById(thumbno).style.width="150px";
			document.getElementById(thumbno).style.top=((100-document.getElementById(thumbno).height)/2)+"px";
		}
	};
}

function cat_home() {
	ajaxcathome.open("GET","CatHomeReturn",true);
	ajaxcathome.onreadystatechange=function() {
		if(ajaxcathome.readyState===4 && ajaxcathome.status===200) document.getElementById("ctgry_home").innerHTML=ajaxcathome.responseText;
	};
	ajaxcathome.send(null);
}

function edit_cat() {
	var catname=document.getElementById("editcat").value;
	ajaxcatedit.open("GET","CatDetailReturn?catname='"+catname+"'",true);
	document.getElementById("cat_name_edit").value=catname;
	ajaxcatedit.onreadystatechange=function() {
		if(ajaxcatedit.responseText=="G") document.getElementById("er2").checked="true";
		else document.getElementById("er1").checked="true";
	};
	ajaxcatedit.send(null);
}