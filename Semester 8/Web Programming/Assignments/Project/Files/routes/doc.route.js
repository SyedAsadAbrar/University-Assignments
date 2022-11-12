const express = require('express');
const router = express.Router();

const doc_controller = require('../controllers/doc.controller');

// a simple test url to check that all of our files are communicating correctly.
router.get('/test', doc_controller.test);

// to load signin page
router.get('/', doc_controller.load);

// to save file
router.post('/save', doc_controller.save);

module.exports = router;