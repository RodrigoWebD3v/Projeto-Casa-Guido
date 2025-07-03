const ComposicaoFamiliar = require('../models/ComposicaoFamiliar');

const criarComposicaoFamiliarRepository = async (data) => new ComposicaoFamiliar(data).save();
const buscarPorId = async (id) => ComposicaoFamiliar.findById(id);
const listar = async () => ComposicaoFamiliar.find();
const atualizar = async (id, data) => ComposicaoFamiliar.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => ComposicaoFamiliar.findByIdAndDelete(id);

module.exports = { criarComposicaoFamiliarRepository, buscarPorId, listar, atualizar, deletar };