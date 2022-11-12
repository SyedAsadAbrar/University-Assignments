var express = require('express');
var router = new express.Router();
var config = require('../config/settings');
var Engine = require('../model/engine');

var index = new Engine.Index();

router.get("/",function (request, response) {
	var html = "";
	var qs = request.query;

	index.search(qs.q, function(resources) {
		html += '[';
		for(i=0; i < resources.length; i++)
			html += resources[i].toJson() + ',';
		html += ']';
		response.render('results',{content : html});
	});
});

module.exports = router;
