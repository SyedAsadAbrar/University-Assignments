function ajax(method,url,mode,handler,caller){
	
	this.requestMethod = method;
	this.requestUrl = url;
	this.ajaxMode = mode;
	if (window.XMLHttpRequest){
		this.xmlhttp = new XMLHttpRequest(); 
	}
	else if (window.ActiveXObject)   // Internet Explorer
	{
		this.xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	this.xmlhttp.parent = this;
	this.callBack = handler;
	this.callerObject = caller;

	
	this.send = function(){

		this.xmlhttp.onreadystatechange = function(){
			if(this.readyState == 4 && this.status == 200){
				this.parent.callBack(this.responseText,this.responseXML,this.parent.callerObject);
			}

		}

		this.xmlhttp.open(this.requestMethod,this.requestUrl,this.ajaxMode);
		this.xmlhttp.send();
	}

}


