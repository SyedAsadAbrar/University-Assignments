var express = require('express');
var router = new express.Router();
var config = require('../config/settings');
var Engine = require('../model/engine');

var index = new Engine.Index();

router.get("/",function (request, response) {
	var html = "";
	var qs = request.query;
	var pageNumber = getPageNumber(request,qs);
	var pageSize = getPageSize(request,qs);
	
	if (qs.q == request.session.lastQuery){
		var results = request.session.pages[pageNumber];
		response.render('results',{content : results,query : qs.q , size:pageSize, page: pageNumber});
	}
	else{
		index.search(qs.q, function(resources) {
			request.session.pages = generatePages(resources,qs.size);
			request.session.lastQuery = qs.q;			
			
			response.render('results',{content : request.session.pages[pageNumber],query : qs.q , size:pageSize, page: pageNumber});
		});
	}
});

function getPageNumber(request,qs){
	var pageNumber = qs.page;
	if (pageNumber == undefined || pageNumber <= 0 || request.session.pages == undefined){
		pageNumber = 1;
	}
	else if (pageNumber >= request.session.pages.length){
		pageNumber = request.session.pages.length - 1;
	}
	return pageNumber;
}

function getPageSize(request,qs){
	var pageSize = qs.size;
	if(pageSize == undefined){
		pageSize = 7;
	}
	return pageSize;
}

function generatePages(resources,pagesize){
	if (pagesize == undefined) pagesize = 7;
	var pagesNum = resources.length / pagesize;	
	var pages = Array();
	for(var i=0; i < pagesNum; i++){
		var html = '[';
		for(j=i*pagesize; j < (i+1)*pagesize && j < resources.length; j++)
			html += resources[j].toJson() + ',';
		html += ']';
		pages[i+1] = html;
	}	
	return pages;
}

module.exports = router;
