const Pessoa = require('../models/Pessoa');

const criarPessoaRepository = async (data) => {
  const pessoa = new Pessoa(data);
  return await pessoa.save();
};

const buscarPorId = async (id) => Pessoa.findById(id);
const listar = async () => Pessoa.find();
const atualizar = async (id, data) => Pessoa.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => Pessoa.findByIdAndDelete(id);

module.exports = { criarPessoaRepository, buscarPorId, listar, atualizar, deletar };