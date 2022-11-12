const express = require('express');
const router = express.Router();

const signin_controller = require('../controllers/signin.controller');

// a simple test url to check that all of our files are communicating correctly.
router.get('/test', signin_controller.test);

// to load signin page
router.get('/', signin_controller.load);

// to do signin
router.post('/', signin_controller.signin)

module.exports = router;