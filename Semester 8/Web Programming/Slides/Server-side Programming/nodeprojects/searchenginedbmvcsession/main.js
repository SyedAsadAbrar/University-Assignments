var express = require('express');
var app = new express();
var session = require('express-session');

app.engine('html',require('./controller/templateengine'));
app.set('views','./view');
app.set('view engine','html');

app.use(session({secret:'mysecret'}));
app.use('/', require('./controller/main'));
app.use('/search', require('./controller/search'));
app.use('/static', express.static('static'));


app.listen(8888, function() { console.log("server listening at port 8888") } );
