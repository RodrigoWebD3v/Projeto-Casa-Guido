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
}, { _id: false });

const PessoaSchema = new mongoose.Schema({
  nome: { type: String, required: true },
  telefone: String,
  dataNascimento: String,
  cpf: { type: String, unique: true, sparse: true },
  rg: { type: String, unique: true, sparse: true },
  naturalidade: String,
  genero: String,
  escolaridade: Number,
  serie: Number,
  estadoCivil: Number,
  situacaoProfissional: Number,
  salario: String,
  cartaoSus: String,
  respPrincipal: Number,
  profissao: String,
  endereco: EnderecoSchema
});

module.exports = mongoose.model('Pessoa', PessoaSchema); 