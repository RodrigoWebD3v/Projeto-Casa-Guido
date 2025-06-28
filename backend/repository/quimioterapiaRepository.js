const Quimioterapia = require('../models/Quimioterapia');

const criarQuimioterapiaRepository = async (data) => new Quimioterapia(data).save();
const buscarPorId = async (id) => Quimioterapia.findById(id);
const buscarPorPaciente = async (pacienteId) => Quimioterapia.find({ paciente: pacienteId });
const listar = async () => Quimioterapia.find();
const atualizar = async (id, data) => Quimioterapia.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => Quimioterapia.findByIdAndDelete(id);
const deletarPorPaciente = async (pacienteId) => Quimioterapia.deleteMany({ paciente: pacienteId });
const atualizarQuimioterapia = async (id, data) => Quimioterapia.findByIdAndUpdate(id, data, { new: true });

module.exports = { 
  criarQuimioterapiaRepository, 
  buscarPorId, 
  buscarPorPaciente,
  listar, 
  atualizar, 
  deletar,
  deletarPorPaciente,
  atualizarQuimioterapia
};