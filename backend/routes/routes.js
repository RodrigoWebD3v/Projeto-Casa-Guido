const express = require('express');
const { register, login, refresh } = require('../api/authController');

const router = express.Router();


module.exports = router;