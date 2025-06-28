const HistoricoSaude = require('../models/HistoricoSaude');

const criarHistoricoSaudeRepository = async (data) => new HistoricoSaude(data).save();
const buscarPorId = async (id) => HistoricoSaude.findById(id);
const listar = async () => HistoricoSaude.find();
const atualizar = async (id, data) => HistoricoSaude.findByIdAndUpdate(id, data, { new: true });
const deletar = async (id) => HistoricoSaude.findByIdAndDelete(id);
const buscarHistoricoSaudePorPaciente = async (pacienteId) => HistoricoSaude.findOne({ paciente: pacienteId });
const atualizarHistoricoSaude = async (id, data) => HistoricoSaude.findByIdAndUpdate(id, data, { new: true });

module.exports = { 
    criarHistoricoSaudeRepository, 
    buscarPorId, 
    listar, 
    atualizar, 
    deletar, 
    buscarHistoricoSaudePorPaciente, 
    atualizarHistoricoSaude 
};