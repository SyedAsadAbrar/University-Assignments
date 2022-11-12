//MongoDB connection
// Connection string: mongodb+srv://test:<test>@cluster0-jnq7t.mongodb.net/test?retryWrites=true&w=majority
// Username: test
// Password: test

const mongoose = require('mongoose')

mongoose.connect(process.env.MONGODB_URL, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useFindAndModify: true,
    useUnifiedTopology: true
})