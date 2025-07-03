const express = require('express');
const pacienteRouter = require('./paciente.routes.js');
const usuarioRouter = require('./usuario.routes.js');
const arquivoRouter = require('./arquivo.routes.js');

const routes = express.Router();

routes.use('/paciente', pacienteRouter);
routes.use('/usuario',  usuarioRouter);
routes.use('/arquivo',  arquivoRouter)

module.exports = routes;