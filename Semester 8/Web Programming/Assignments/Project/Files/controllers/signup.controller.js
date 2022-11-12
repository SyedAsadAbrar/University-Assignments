const User = require('../models/User.model');

const validator = require('validator')

var nodemailer = require('nodemailer');

//Simple version, without validation or sanitation
exports.test = function (req, res) {
    res.send('Greetings from the Test controller - Signup!');
};

exports.load = function (req, res) {
    if(req.session.user){
        res.redirect('/');
    }
    else{
        res.render('signup.ejs');
    }
};

exports.signup = async function (req, res) {
    // Create a new user
    try {
        var name = req.body.fullName;
        var email = req.body.email;
        var password = req.body.password;
        var confPassword = req.body.confPassword;

        if(name.length == 0){
            var error = new Error();
            error.message = 'Name field cannot be empty';
            return res.status(401).send(error)
        }

        if(email.length == 0){
            var error = new Error();
            error.message = 'Email field cannot be empty';
            return res.status(401).send(error)            
        }
        else if(!validator.isEmail(email)){
            var error = new Error();
            error.message = 'Entered Email address is invalid';
            return res.status(401).send(error)
        }

        if(password.length == 0){
            var error = new Error();
            error.message = 'Password field cannot be empty';
            return res.status(401).send(error)
        }
        else if(password.length < 7){
            var error = new Error();
            error.message = 'Password must be at least 7 characters';
            return res.status(401).send(error)
        }
        else{
            if(confPassword.length == 0){
                var error = new Error();
                error.message = 'Confirm Password field cannot be empty';
                return res.status(401).send(error)
            }
            else if(confPassword.length < 7){
                var error = new Error();
                error.message = 'Password must be at least 7 characters';
                return res.status(401).send(error)
            }
            else if(password != confPassword)
            {
                var error = new Error();
                error.message = 'Passwords do not match';
                return res.status(401).send(error)                
            }
        }

        const user = new User(req.body);
        console.log(user);
        await user.save();
        console.log("User saved");

        var transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'documents.emailer@gmail.com',
            pass: '123pass456'
        }
        });

        var mailOptions = {
        from: 'documents.emailer@gmail.com',
        to: email,
        subject: 'Welcome to Documents!',
        html: 'Hello ' + name + ',<br>Welcome to Documents! Thank you for choosing us for your productivity needs. We hope you enjoy our product! :)<br>Regards,<br><img src="https://i.imgur.com/jHYD4qS.png?1" alt="Documents."><br>Documents Team'
        };

        transporter.sendMail(mailOptions, function(error, info){
        if (error) {
            console.log(error);
        } else {
            console.log('Email sent: ' + info.response);
        }
        });

        res.status(201).send({});
    } catch (error) {
        console.log('Error:', error);
        res.status(400).send(error);
    }
};