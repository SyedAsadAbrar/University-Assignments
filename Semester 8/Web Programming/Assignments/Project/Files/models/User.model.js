
const mongoose = require('mongoose')
const bcrypt = require('bcryptjs')
const jwt = require('jsonwebtoken')

const userSchema = mongoose.Schema({
    fullName: {
        type: String,
        required: true,
        trim: true
    },
    email: {
        type: String,
        required: true,
        unique: true,
        lowercase: true
    },
    password: {
        type: String,
        required: true,
        minLength: 7
    },
})

userSchema.pre('save', async function (next) {
    // Hash the password before saving the user model
    console.log("Before saving user")
    const user = this

    if (user.isModified('password')) {
        console.log("Password modified")
        user.password = await bcrypt.hash(user.password, 8)
    }
    var email = user.email;
    const user2 = await User.findOne({ email} )
    if (user2){
        //console.log("Email address is currently in use'")
        var error = new Error();
        error.message = 'Email address is currently in use';
        throw error;
    }
    console.log("End of pre function")
    next()
})

userSchema.statics.findByCredentials = async (email, password) => {
    // Search for a user by email and password.
    const user = await User.findOne({ email} )
    if (!user) {
        console.log("user not found")
        var error = new Error();
        error.message = 'User with the typed email address doesn\'t exist';
        throw error;
    }
    console.log("user found")
    const isPasswordMatch = await bcrypt.compare(password, user.password)
    console.log("checking password")
    if (!isPasswordMatch) {
        console.log("password is incorrect")
        var error = new Error();
        error.message = 'Invalid Password';
        throw error;
    }
    console.log("password correct")
    return user
}

const User = mongoose.model('User', userSchema)

module.exports = User