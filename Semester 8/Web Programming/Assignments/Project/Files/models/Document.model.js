
const mongoose = require('mongoose')
const bcrypt = require('bcryptjs')
const jwt = require('jsonwebtoken')

const docSchema = mongoose.Schema({
    name: {
        type: String,
        required: true,
        trim: true,
    },
    content: {
        type: String,
        required: true
    },
    owner:{
        type: String,
        required: true,
        lowercase: true
    },
    // collaborators: [{
    //     owner
    // }],
    date: Date 
})

docSchema.statics.findByCredentials = async (docName, email) => {
    // Search for a user by email and password.

    const doc = await Document.findOne({name:docName, owner:email})
    if (!doc) {
        console.log("Specified document not found")
        var error = new Error();
        error.message = 'Document not found';
        throw error;
    }
    console.log("Document found")

    return doc
}

// docSchema.statics.findByCredentials = async (email) => {
//     // Search for a user by email and password.

//     const doc = await Document.find({owner:email});
//     console.log(doc);
//     if (!doc) {
//         console.log("Specified document not found")
//         var error = new Error();
//         error.message = 'Document not found';
//         throw error;
//     }
//     console.log("Document(s) found")
//     return doc
// }

const Document = mongoose.model('Document', docSchema)

module.exports = Document