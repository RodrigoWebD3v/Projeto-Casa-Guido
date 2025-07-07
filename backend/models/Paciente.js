const mongoose = require('mongoose');

const PacienteSchema = new mongoose.Schema({
  pessoa: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa', required: true },
  status: Boolean,
  nomeMae: String,
  nomePai: String,
  nomeOutro: String,
  recebeRemuneracao: Number,
  remuneracao: String,
  recebeBpc: Number,
  valorBpc: String,
  diagnostico: String,
  profissionalResponsavel: String,
  escolaNome: String,
  anoEscolar: String,
  tamRoupa: String,
  tamCalcado: String,
  origenInfoOng: String,
  idBackend: String,
  alterado: { type: Boolean, default: false },
  observacoes: [String],
  responsavel: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa' },
  conjugeResponsavel: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa' },
  outroResponsavel: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa' },
  ubs: { type: mongoose.Schema.Types.ObjectId, ref: 'Ubs' },
  cras: { type: mongoose.Schema.Types.ObjectId, ref: 'Cras' }
}, { timestamps: true });

module.exports = mongoose.model('Paciente', PacienteSchema); 