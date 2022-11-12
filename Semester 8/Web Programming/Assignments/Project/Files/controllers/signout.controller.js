const User = require('../models/User.model');

exports.signout = async function (req, res){
    console.log("Signout function");
    if(req.session.user) {
        delete req.session.user;
    }
    res.redirect('/'); 
};