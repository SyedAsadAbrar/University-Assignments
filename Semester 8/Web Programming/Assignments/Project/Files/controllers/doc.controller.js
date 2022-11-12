const User = require('../models/User.model');

const Document = require('../models/Document.model');

//Simple version, without validation or sanitation
exports.test = function (req, res) {
    res.send('Greetings from the Test controller!');
};

exports.load = function (req, res) {
	if(req.session.user){
        res.render('doc.ejs',{name: req.session.user.name, doc: req.session.doc});
    }
    else{
        res.redirect('/');
    }
};

exports.save = async function(req,res){
    const doc = req.session.doc;
    const filter = { name: doc.name, owner: doc.owner, date: doc.date};
    const update = { content: req.body.content};

    oldDoc = await Document.findOneAndUpdate(filter, update, {
        new: true
    });
    res.status(201).send({oldDoc});
};