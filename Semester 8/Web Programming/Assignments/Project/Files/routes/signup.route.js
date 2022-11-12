const express = require('express');
const router = express.Router();

const signup_controller = require('../controllers/signup.controller');

// a simple test url to check that all of our files are communicating correctly.
router.get('/test', signup_controller.test);

// to load signup page
router.get('/', signup_controller.load);

// to do signup
router.post('/', signup_controller.signup);

module.exports = router;