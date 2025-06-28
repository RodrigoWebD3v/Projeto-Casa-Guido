const mongoose = require('mongoose');

const CrasSchema = new mongoose.Schema({
  municipio: String,
  bairro: String,
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente' }
});

module.exports = mongoose.model('Cras', CrasSchema); 