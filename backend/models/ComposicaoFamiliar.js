const mongoose = require('mongoose');

const ComposicaoFamiliarSchema = new mongoose.Schema({
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente' },
  nome: String,
  parentesco: String,
  dataNascimento: String,
  estudaOpcional: Number,
  serie: String,
  trabalhaOpcional: Number,
  renda: String
});

module.exports = mongoose.model('ComposicaoFamiliar', ComposicaoFamiliarSchema); 