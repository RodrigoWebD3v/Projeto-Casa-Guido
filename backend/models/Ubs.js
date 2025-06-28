const mongoose = require('mongoose');

const UbsSchema = new mongoose.Schema({
  municipio: String,
  bairro: String,
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente' }
});

module.exports = mongoose.model('Ubs', UbsSchema); 