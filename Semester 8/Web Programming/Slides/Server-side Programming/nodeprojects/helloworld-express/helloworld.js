var express = require('express');
var app = new express();

app.get("/", function(request,response){
	response.send("Hello world");
});

app.listen(8888,function(){ console.log("Server listening at port 8888"); });