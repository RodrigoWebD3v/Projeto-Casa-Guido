const mongoose = require('mongoose');

const CirurgiaSchema = new mongoose.Schema({
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente', required: true },
  nome: { type: String, required: true },
  data: { type: String, required: true },
  cid: { type: String },
  observacoes: { type: String }
}, { timestamps: true });

module.exports = mongoose.model('Cirurgia', CirurgiaSchema); 