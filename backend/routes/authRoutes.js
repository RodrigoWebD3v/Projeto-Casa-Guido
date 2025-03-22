const express = require('express');
const { registrar, login, refresh } = require('../api/authController');

const authRouter = express.Router();

authRouter.post('/register', registrar);
authRouter.post('/login', login);
authRouter.post('/refresh', refresh);

module.exports = authRouter;