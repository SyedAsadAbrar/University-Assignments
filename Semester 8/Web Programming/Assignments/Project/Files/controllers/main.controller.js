const User = require('../models/User.model');

const Document = require('../models/Document.model');

var path = require("path");

var fs = require('fs')

//Simple version, without validation or sanitation
exports.test = function (req, res) {
    res.send('Greetings from the Test controller!' + req);
};

exports.load = async function (req, res) {
    if(!req.session.user) {
        res.redirect('/signin');
    }
    else{
        const docs = await Document.find({owner:req.session.user.email});
        console.log(docs)
        res.render('mainScreen.ejs',{name: req.session.user.name, docs: docs});
    }
};

exports.create = async function(req, res){
    if(!req.session.user) {
        res.redirect('/signin');
    }
    else{
        date = new Date();
        const docs = await Document.find({owner:req.session.user.email});
        docOptions = {
            name: req.session.user.name + ' ' + (docs.length + 1),
            content: req.session.user.name + " - doc " + (docs.length + 1), 
            owner: req.session.user.email, 
            date: date.toISOString().slice(0,10)
        };
        const doc = new Document(docOptions);
        console.log(doc);
        await doc.save();
        req.session.doc = doc;
        res.redirect('/doc');        
    }
};

exports.edit = async function(req, res){
    const doc = await Document.findOne({name:req.body.name, owner:req.body.owner});
    req.session.doc = doc;
    res.status(201).send({});
};