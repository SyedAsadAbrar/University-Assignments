const User = require('../models/User.model')

const auth = async(req, res, next) => {
    const token = req.header('Authorization').replace('Bearer ', '')
    const data = jwt.verify(token, process.env.JWT_KEY)
    try {
        const user = await User.findOne({ _id: data._id})
        if (!user) {
            throw new Error()
        }
        req.user = user
        req.token = token
        next()
    } catch (error) {
        var error = new Error();
        error.message = 'Not authorized to access this resource';
        res.status(401).send(error)
    }

}

module.exports = auth