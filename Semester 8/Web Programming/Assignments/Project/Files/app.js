// yarn add express mongodb mongoose bcryptjs validator express-session connect-mongodb-session cookie-parser ejs nodemailer
// yarn add env-cmd nodemon --dev

var express = require('express')
const auth = require('./middleware/auth')

require('./db/db')

const port = process.env.PORT

const signin = require('./routes/signin.route'); 
const signup = require('./routes/signup.route'); 
const doc = require('./routes/doc.route'); 
const main = require('./routes/main.route'); 
const signout = require('./routes/signout.route');

const session = require('express-session');
var cookieParser = require('cookie-parser');
// const MongoDBStore = require('connect-mongodb-session')(session);

// const store = new MongoDBStore({
//     uri: process.env.MONGODB_URL,
//     collection: 'sessions'
// });

var app = new express();

app.use(express.urlencoded({ extended: true }))
app.use(express.json())

app.use(cookieParser());
app.use(session({secret: "Shh, its a secret!"}));

app.set('views','./views');
app.set('view engine','ejs');

app.use('/signin', signin)

app.use('/signup', signup)

app.use('/signout', signout)

app.use("/static", express.static(__dirname + '/static'));

app.use("/doc", doc)

app.use("/main", main)



app.use("/", async function (req, res){
	if(req.session.user){
		res.redirect('main');
	}
	else{
		res.redirect('signin');
	}
})

app.listen(port, () => {
    console.log(`Server running on port ${port}`)
});
