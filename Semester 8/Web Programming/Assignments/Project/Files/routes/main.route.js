const express = require('express');
const router = express.Router();

const main_controller = require('../controllers/main.controller');

// a simple test url to check that all of our files are communicating correctly.
router.get('/test', main_controller.test);

// to load signin page
router.get('/', main_controller.load);

// to create new document
router.get('/create', main_controller.create);

// to open existing document
router.post('/edit', main_controller.edit);

module.exports = router;