const Cirurgia = require('../models/Cirurgia');

const criarCirurgiaRepository = async (data) => new Cirurgia(data).save();
const buscarPorId = async (id) => Cirurgia.findById(id);
const buscarPorPaciente = async (pacienteId) => Cirurgia.find({ paciente: pacienteId });
const listar = async () => Cirurgia.find();
const atualizar = async (id, data) => Cirurgia.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => Cirurgia.findByIdAndDelete(id);
const deletarPorPaciente = async (pacienteId) => Cirurgia.deleteMany({ paciente: pacienteId });
const atualizarCirurgia = async (id, data) => Cirurgia.findByIdAndUpdate(id, data, { new: true });

module.exports = { 
  criarCirurgiaRepository, 
  buscarPorId, 
  buscarPorPaciente,
  listar, 
  atualizar, 
  deletar,
  deletarPorPaciente,
  atualizarCirurgia
};