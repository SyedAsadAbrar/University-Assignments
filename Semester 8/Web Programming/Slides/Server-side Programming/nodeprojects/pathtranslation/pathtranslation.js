var http = require('http');
var fs = require('fs');

http.createServer(function (request, response) {

  basePath = "C:\\nodeprojects\\pathtranslation";  // root folder

  filePath = basePath + request.url;

  fs.readFile(filePath,function(err,contents){
	if (err) {
      		response.writeHead(404, {'Content-Type': 'text/html'});
      		response.end("404 Not Found");
    	}
	else{
		response.writeHead(200, {'Content-Type': 'text/html'});
		response.write(contents);
		response.end();
	}
  });

}).listen(8888);