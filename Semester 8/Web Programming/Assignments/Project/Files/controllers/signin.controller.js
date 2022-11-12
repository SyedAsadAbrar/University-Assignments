const User = require('../models/User.model');

var path = require("path");

const session = require('express-session');

//Simple version, without validation or sanitation
exports.test = function (req, res) {
    res.send('Greetings from the Test controller!');
};

exports.load = function (req, res) {
    if(req.session.user){
        res.redirect('/');
    }
    else{
        res.render('signin.ejs');
    }
};

exports.signin = async function (req, res) {
    //Login a registered user
    try {
        const { email, password } = req.body
        const user = await User.findByCredentials(email, password)
        console.log("Searching for user")
        if (!user) {
            var error = new Error();
            error.message = 'Login failed! Check authentication credentials';
            return res.status(401).send(error)
        }
        console.log("User found")
        console.log(req.session)
        req.session.user = {
            email: user.email,
            name: user.fullName
        };
        console.log(req.session)
        console.log("Stored in session")
        return res.status(200).send({});
    } catch (error) {
        res.status(400).send(error)
    }
    return true
    // res.send(req.body.email + req.body.password);
};