const express = require('express');

const { buscarUsuarioController } = require('../api/usuarioController');

const routes = express.Router();

routes.get('/usuario', buscarUsuarioController);

module.exports = routes;