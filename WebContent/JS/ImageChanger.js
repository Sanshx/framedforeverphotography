var ajaxob;
var ajaxobj;
var ajaxinit;
var img1=null;
var img2=null;
var imgtemp=null;
var i=0;
var imgchange;
var context;
function init() {
	imgchange = document.getElementById("imgchange");
	ajaxob =new XMLHttpRequest();
	ajaxob.open("GET","HomeImgReturn?req=new&u="+Math.random(),true);
	ajaxob.onreadystatechange=function() {
		if(ajaxob.readyState===4 && ajaxob.status===200) {
			img1="Pics/"+ajaxob.responseText+".JPG?"+Math.random();
			ajaxobj = new XMLHttpRequest();
			setInterval(sendreq, 5000);
	    //	sendreq();
		}
	};
	ajaxob.send(null);
}

function sendreq() {
	ajaxobj.open("GET","HomeImgReturn?req=old&u="+Math.random(),true);
	ajaxobj.onreadystatechange=function () {
		if(ajaxobj.readyState===4 && ajaxobj.status===200) {
			img2="Pics/"+ajaxobj.responseText+".JPG?"+Math.random();
			var image1 = new Image();
			image1.src = img1;
			var image2 = new Image();
			image2.src = img2;
			image1.onload = function(){
				imgchange.style.opacity=0;
				imgchange.src=image1.src;
				imgchange.onload=render;
				i=0;
				fadein();
			//	setTimeout(sendreq, 5000);
			};
			image1.src=image2.src;
		}
	};
	ajaxobj.send(null);
	
}
function fadein() {
	if(i<1)
	{
		i=i+0.015;
		imgchange.style.opacity=i;
		setTimeout(fadein, 5);
	}
}
function fadeout() {
	if(i>0)
	{
		i=i-0.01;
		imgchange.style.opacity=i;
		setTimeout(fadeout, 5);
	}
}

function render()
{
	var width = imgchange.naturalWidth;
	var height = imgchange.naturalHeight;
	if(width/height<=3/2)
	{
		imgchange.style.height="600px";
		imgchange.style.width="auto";
		imgchange.style.top="0px";
		imgchange.style.left=(900-imgchange.width)/2+"px";
	}
	else
	{
		imgchange.style.height="auto";
		imgchange.style.width="900px";
		imgchange.style.top=(600-imgchange.height)/2+"px";
		imgchange.style.left="0px";
	}
}

