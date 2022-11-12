var express = require('express');
var router = new express.Router();


router.get(["/","/index"],function (request,response) {	  	
	response.render('index');
});

module.exports = router;
