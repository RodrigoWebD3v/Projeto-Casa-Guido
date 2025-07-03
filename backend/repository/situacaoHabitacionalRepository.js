const SituacaoHabitacional = require('../models/SituacaoHabitacional');

const criarSituacaoHabitacionalRepository = async (data) => new SituacaoHabitacional(data).save();
const buscarPorId = async (id) => SituacaoHabitacional.findById(id);
const listar = async () => SituacaoHabitacional.find();
const atualizar = async (id, data) => SituacaoHabitacional.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => SituacaoHabitacional.findByIdAndDelete(id);
const buscarSituacaoHabitacionalPorPaciente = async (pacienteId) => SituacaoHabitacional.findOne({ paciente: pacienteId });
const atualizarSituacaoHabitacional = async (id, data) => SituacaoHabitacional.findByIdAndUpdate(id, data, { new: true });

module.exports = { 
    criarSituacaoHabitacionalRepository, 
    buscarPorId, 
    listar, 
    atualizar, 
    deletar, 
    buscarSituacaoHabitacionalPorPaciente, 
    atualizarSituacaoHabitacional 
};