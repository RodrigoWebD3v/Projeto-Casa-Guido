const ProfissionalResponsavel = require('../models/ProfissionalResponsavel');

const criarProfissionalResponsavelRepository = async (data) => new ProfissionalResponsavel(data).save();
const buscarPorId = async (id) => ProfissionalResponsavel.findById(id);
const listar = async () => ProfissionalResponsavel.find();
const atualizar = async (id, data) => ProfissionalResponsavel.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => ProfissionalResponsavel.findByIdAndDelete(id);

module.exports = { criarProfissionalResponsavelRepository, buscarPorId, listar, atualizar, deletar }; 