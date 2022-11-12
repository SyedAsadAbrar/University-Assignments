var http = require('http');
var fs = require('fs');
var url = require('url');

Engine = require('./engine.js');


http.createServer(function (request, response) {

  basePath = "C:\\nodeprojects\\searchengine";

  var u = url.parse(request.url,true);
    
  if(u.pathname == "/search"){
	  
	  var qs = u.query;
	  var index = new Engine.Index();
	  var resources = index.search(qs.q);
	  var html = "";
	  
	  for(i=0; i < resources.length; i++)
		html += resources[i].generateHtml();
	  
	  response.writeHead(200, {'Content-Type': 'text/html'});
	  response.write(html);
	  response.end();	  
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
