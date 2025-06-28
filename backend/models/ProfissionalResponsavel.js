const mongoose = require('mongoose');

const ProfissionalResponsavelSchema = new mongoose.Schema({
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente' },
  nome: String,
  crm: String
});

module.exports = mongoose.model('ProfissionalResponsavel', ProfissionalResponsavelSchema); 