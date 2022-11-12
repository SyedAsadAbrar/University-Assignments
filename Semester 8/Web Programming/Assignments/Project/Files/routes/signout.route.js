const express = require('express');
const router = express.Router();

const signout_controller = require('../controllers/signout.controller');

// to signout
router.get('/', signout_controller.signout);

module.exports = router;