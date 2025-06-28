const mongoose = require('mongoose');

const EnderecoSchema = new mongoose.Schema({
  cep: String,
  logradouro: String,
  numero: String,
  complemento: String,
  unidade: String,
  bairro: String,
  localidade: String,
  uf: String,
  estado: String,
  regiao: String,
  referencia: String,
  pais: { type: String, default: 'Brasil' }
});

module.exports = mongoose.model('Endereco', EnderecoSchema); 