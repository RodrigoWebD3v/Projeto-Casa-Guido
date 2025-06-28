const Endereco = require('../models/Endereco');

const criarEnderecoRepository = async (dtoEndereco) => {
  const endereco = new Endereco(dtoEndereco);
  return await endereco.save();
};

const buscarEnderecoPorId = async (id) => {
  return await Endereco.findById(id);
};

const listarEnderecos = async () => {
  return await Endereco.find();
};

const atualizarEndereco = async (id, dtoEndereco) => {
  return await Endereco.findByIdAndUpdate(id, dtoEndereco, { new: true });
};

const deletarEndereco = async (id) => {
  return await Endereco.findByIdAndDelete(id);
};

module.exports = {
  criarEnderecoRepository,
  buscarEnderecoPorId,
  listarEnderecos,
  atualizarEndereco,
  deletarEndereco
};