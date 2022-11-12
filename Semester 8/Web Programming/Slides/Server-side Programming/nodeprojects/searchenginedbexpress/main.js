var express = require('express');
var fs = require('fs');
var Engine = require('./engine.js');

var index = new Engine.Index();
var app = new express();

var basePath = "C:\\nodeprojects\\searchenginedbexpress";

app.get(["/","/index"],function (request,response) {	  
	filePath = basePath + "\\index.html";

	fs.readFile(filePath,function(err,contents){		
		response.send(contents.toString());
	});
  
});

app.get("/search",function (request, response) {
	var html = "";
	var qs = request.query;

	index.search(qs.q, function(resources) {

		for(i=0; i < resources.length; i++)
			html += resources[i].generateHtml();

		//response.send(html);
		filePath = basePath + "\\results.html";

		fs.readFile(filePath,function(err,contents){		
			response.send(contents.toString().replace("<!-- content -->",html));
		});
	});
});

app.use("/static", express.static("static"));

app.listen(8888, function() { console.log("server listening at port 8888") } );
