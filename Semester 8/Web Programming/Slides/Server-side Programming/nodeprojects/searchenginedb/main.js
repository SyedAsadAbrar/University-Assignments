var http = require('http');
var fs = require('fs');
var url = require('url');

var Engine = require('./engine.js');
var index = new Engine.Index();

http.createServer(function (request, response) {

  basePath = "C:\\nodeprojects\\searchengine";
  filePath = basePath + request.url;
  var u = url.parse(request.url,true);
    
  if(u.pathname == "/search"){
	  
	  var html = "";
	  var qs = u.query;
	  
	  index.search(qs.q, function(resources) {
	  
	  for(i=0; i < resources.length; i++)
		html += resources[i].generateHtml();
	  	  
	  response.writeHead(200, {'Content-Type': 'text/html'});
	  response.write(html);
	  response.end();	  
	  });
  }
  else {
	  
	  filePath = basePath + "\\index.html";

	  fs.readFile(filePath,function(err,contents){
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.write(contents);
		response.end();
	  });
  }
  

}).listen(8888);

console.log("server listening at port 8888");
