const mongoose = require('mongoose');

const SituacaoHabitacionalSchema = new mongoose.Schema({
  paciente: { type: mongoose.Schema.Types.ObjectId, ref: 'Paciente' },
  comoAdquiriuCasa: Number,
  area: Number,
  numeroComodos: Number,
  material: Number,
  bens: [Number],
  meioDeTransporte: Number,
  meioDeComunicao: Number,
  possuiBanheiros: Number
});

module.exports = mongoose.model('SituacaoHabitacional', SituacaoHabitacionalSchema); 