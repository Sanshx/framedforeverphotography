var ajaxalb=new XMLHttpRequest();

function thumbrtrn(id,name){
//	console.log("Here we go .."+id+" "+name);
	ajaxalb.open("GET","AlbumThumb?id="+id+"&name="+name+"&u="+Math.random(), false);
	ajaxalb.onreadystatechange=function() {
		if(ajaxalb.readyState===4 && ajaxalb.status===200) {
			document.getElementById(id).style.backgroundImage='url(Thumbs/'+ajaxalb.responseText+'.JPG)';
//			console.log("<img src=Thumbs/"+ajaxalb.responseText+".JPG>");
		}
	};
	ajaxalb.send(null);
}

