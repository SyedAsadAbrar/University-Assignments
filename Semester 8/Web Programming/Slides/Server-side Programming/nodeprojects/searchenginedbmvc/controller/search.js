var express = require('express');
var router = new express.Router();
var config = require('../config/settings');
var Engine = require('../model/engine');

var index = new Engine.Index();

router.get("/",function (request, response) {
	var html = "";
	var qs = request.query;

	index.search(qs.q, function(resources) {

		for(i=0; i < resources.length; i++)
			html += generateResourceHtml(resources[i].toJson());

		response.render('results',{content : html});
	});
});

function generateResourceHtml(json){
	var obj = JSON.parse(json);
	
	var html = '<p>';
	html += '<a href="' + obj.url + '">' + obj.title + '</a>';
	html += '<br/>';
	html += obj.description ;
	html += '</p>';
	
	return html;
}

module.exports = router;
