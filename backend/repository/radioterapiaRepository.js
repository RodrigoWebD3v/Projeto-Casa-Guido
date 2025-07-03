const RadioTerapia = require('../models/RadioTerapia');

const criarRadioTerapiaRepository = async (data) => new RadioTerapia(data).save();
const buscarPorId = async (id) => RadioTerapia.findById(id);
const buscarPorPaciente = async (pacienteId) => RadioTerapia.find({ paciente: pacienteId });
const listar = async () => RadioTerapia.find();
const atualizar = async (id, data) => RadioTerapia.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => RadioTerapia.findByIdAndDelete(id);
const deletarPorPaciente = async (pacienteId) => RadioTerapia.deleteMany({ paciente: pacienteId });
const atualizarRadioterapia = async (id, data) => RadioTerapia.findByIdAndUpdate(id, data, { new: true });

module.exports = { 
  criarRadioTerapiaRepository, 
  buscarPorId, 
  buscarPorPaciente,
  listar, 
  atualizar, 
  deletar,
  deletarPorPaciente,
  atualizarRadioterapia
};