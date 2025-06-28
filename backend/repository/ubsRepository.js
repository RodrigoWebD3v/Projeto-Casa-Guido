const Ubs = require('../models/Ubs');

const criarUbsRepository = async (data) => new Ubs(data).save();
const buscarPorId = async (id) => Ubs.findById(id);
const listar = async () => Ubs.find();
const atualizar = async (id, data) => Ubs.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => Ubs.findByIdAndDelete(id);

module.exports = { criarUbsRepository, buscarPorId, listar, atualizar, deletar };