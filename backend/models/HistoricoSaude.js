const mongoose = require('mongoose');

const HistoricoSaudeSchema = new mongoose.Schema({
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente' },
  doencasFamilia: [Number],
  medicamentosUsoContinuo: String,
  localProcuraAtendimento: [Number],
  recebeBeneficio: Number,
  beneficioDescricao: String
});

module.exports = mongoose.model('HistoricoSaude', HistoricoSaudeSchema); 