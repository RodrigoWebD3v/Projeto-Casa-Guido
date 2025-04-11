const express = require('express');

const { buscarUsuarioController } = require('../api/usuarioController');
const { criarPacienteController } = require('../api/domainController/criarPacienteController');

const routes = express.Router();

routes.get('/usuario', buscarUsuarioController);

routes.post('/paciente', criarPacienteController);

module.exports = routes;