const mongoose = require('mongoose');

const RadioTerapiaSchema = new mongoose.Schema({
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente', required: true },
  dataInicio: { type: String, required: true },
  dataUltimaSessao: { type: String },
  observacoes: { type: String }
}, { timestamps: true });

module.exports = mongoose.model('RadioTerapia', RadioTerapiaSchema); 