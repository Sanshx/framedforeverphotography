var pics = new Array();
var cap = new Array();

var i = 0;
var o = 0;
var n = 4;
var t = 0;
var centerimg;
var j = 0;
var ajaxinit=new XMLHttpRequest();
var ajaxdir=new XMLHttpRequest();
var scroll=0;

function fadein() {
	if(o<1)
	{
		o=o+0.01;
		document.getElementById("centerimg").style.opacity=o;
		setTimeout(fadein, 5);
	}
}

function chromeUp(){
    setTimeout(function(){
        scrollTo(0,-1);
    },0);
}

function pageUp() {
	window.scrollTo(0, 0);
}
function pageScroll() {
	scroll++;
	if(scroll<15)
	window.scrollBy(0,10); 
	scrolldelay = setTimeout('pageScroll()',50-scroll); 
}
function init(cat_name) {
	var ctgry_name=cat_name;
	ajaxinit.open("GET","CategorySpecInit?ctgry_name="+ctgry_name+"&u="+Math.random(),true);
	ajaxinit.onreadystatechange= function() {
		if(ajaxinit.readyState===4 && ajaxinit.status===200) {
		//	console.log("Initiasation via ajax complete");
			ajaxdir.open("GET","CategorySpecDir?u="+Math.random(),true);
			ajaxdir.onreadystatechange= initcomp;
			ajaxdir.send(null);
		}
		document.getElementById("cat").setAttribute("onmouseover", "zin()");
		document.getElementById("cat").setAttribute("onmouseout", "zout()");
		thumbload();
	};
	ajaxinit.send(null);
}

function initcomp() {
	if(ajaxdir.readyState===4 && ajaxdir.status===200) {
			var x=ajaxdir.responseXML.getElementsByTagName("IMAGE")[0];
			var hash=x.getElementsByTagName("HASH")[0].childNodes[0].nodeValue;
			var cptn=x.getElementsByTagName("CAPTION")[0].childNodes[0].nodeValue;
			if(hash!=="END") {
				pics[j]=hash+".JPG";	
				cap[j]=cptn;
			}		
			j++;
			ajaxdir.open("GET","CategorySpecDir?u="+Math.random(),true);
			ajaxdir.onreadystatechange = initcomp;
			while (hash!="END") {
				ajaxdir.send(null);
			}
		thumbload();
	}
};
function changeprev()
{
	centerimg.style.visibility="hidden";
	if(i<t||i>t+n)
		t=i-(i%n);
	thumbrefresh();
	i--;
	if(i<0){i=pics.length-1;thumbprev();};
	centerchange("Pics/"+pics[i]);
	if(i%n==n-1)
	{
		thumbprev();
		imgchange(n);
	}
	else
		imgchange(i%n+1);
}
function changenext()
{
	centerimg.style.visibility="hidden";
	if(i<t||i>t+n)
		t=i-(i%n);
	thumbrefresh();
	i++;
	centerchange("Pics/"+pics[i]);
	if(i%n==0)
	{
		thumbnext();
		imgchange(1);
	}
	else
		imgchange(i%n+1);
	if(i>pics.length-1)thumbload();
}
function thumbload()
{
	centerimg = document.getElementById("centerimg");
	i=0;
	n=6;
	t=0;
	thumbrefresh();
	imgchange(1);
}
function thumbreinit()
{
	for(var temp=0;temp<n;temp++)
		document.getElementById("catsub"+(temp+1)).style.backgroundColor="#111111";
}
function thumbrefresh()
{
	if(i>t&&i<t+n)
		document.getElementById("catsub"+(i%n+1)).style.backgroundColor="#444444";
	for(var temp=0;temp<n;temp++)
		document.getElementById("thumb"+(temp+1)).style.visibility="hidden";
	for(var temp=0;(temp<n)&&((pics.length)>(temp+t));temp++)
	{
		setthumb("thumb"+(temp+1),"Thumbs/"+pics[t+temp]);
		document.getElementById("thumb"+(temp+1)).style.visibility="visible";
	}
}
function thumbnext()
{
	t=t+n;
	if(t>pics.length)t=0;
	thumbreinit();
	thumbrefresh();
}
function thumbprev()
{
	t=t-n;
	if(t<0)t=pics.length-(pics.length%n);
	thumbreinit();
	thumbrefresh();
}
function imgchange(k)
{
	thumbreinit();
	document.getElementById("catsub"+k).style.backgroundColor="#444444";
	i=t+k-1;
	centerchange("Pics/"+pics[i]);
}

function centerchange(imgsrc)
{
	var image = new Image();
	image.src = imgsrc;
	image.onload = function(){	
//		setTimeout(function(){
		centerimg.src=image.src;
		centerimg.onload=render;
		centerimg.style.visibility="visible";
		o=0;
		fadein();
	};
	document.getElementById("caption").innerHTML=cap[i];
}
function render()
{
	var width = centerimg.naturalWidth;
	var height = centerimg.naturalHeight;
	if(width/height<=3/2)
	{
		centerimg.style.height="800px";
		centerimg.style.width="auto";
		centerimg.style.top="0px";
	}
	else
	{
		centerimg.style.height="auto";
		centerimg.style.width="1200px";
		centerimg.style.top=(800-centerimg.height)/2+"px";
	}
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
			document.getElementById(thumbno).style.top=(100-document.getElementById(thumbno).height)/2+"px";
		}
	};
}
function zin() {
//	console.log("zin");
	document.getElementById("inprev").style.zIndex="-1";
	document.getElementById("innext").style.zIndex="-1";
	document.getElementById("catimg").style.zIndex="-1";
}
function zout() {
//	console.log("zout");
	document.getElementById("inprev").style.zIndex="1";
	document.getElementById("innext").style.zIndex="1";
	document.getElementById("catimg").style.zIndex="0";
}