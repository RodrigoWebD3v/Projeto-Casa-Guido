const express = require('express');
const pacienteRouter = require('./pacienteRoutes.js');

const { buscarUsuarioController } = require('../api/usuarioController');

const routes = express.Router();

routes.use('/paciente', pacienteRouter);

module.exports = routes;