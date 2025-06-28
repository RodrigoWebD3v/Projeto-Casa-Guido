const Cras = require('../models/Cras');

const criarCrasRepository = async (data) => new Cras(data).save();
const buscarPorId = async (id) => Cras.findById(id);
const listar = async () => Cras.find();
const atualizar = async (id, data) => Cras.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => Cras.findByIdAndDelete(id);

module.exports = { criarCrasRepository, buscarPorId, listar, atualizar, deletar };