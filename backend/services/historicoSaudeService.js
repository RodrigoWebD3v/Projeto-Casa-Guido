const { historicoSaudeDTO } = require("../dto/pacienteDTO/historicoSaudeDTO");
const { criarHistoricoSaudeRepository, atualizarHistoricoSaude, buscarHistoricoSaudePorPaciente } = require("../repository/historicoSaudeRepository");

const criarHistoricoSaudeService = async (historicoSaude,  pacienteId) => {
  try {
    const historicoSaudeDT = await historicoSaudeDTO(historicoSaude, pacienteId);
    const dadosSalvos = await criarHistoricoSaudeRepository(historicoSaudeDT);
    return dadosSalvos;
  } catch (e) {
    console.log(e)
    return [];
  }
};

const editarHistoricoSaudeService = async (pacienteId, historicoSaude) => {
  try {
    // Busca o histórico existente do paciente
    const historicoExistente = await buscarHistoricoSaudePorPaciente(pacienteId);
    
    const historicoSaudeDT = await historicoSaudeDTO(historicoSaude, pacienteId);
    
    if (historicoExistente) {
      // Se existe, atualiza
      const dadosAtualizados = await atualizarHistoricoSaude(historicoExistente._id, historicoSaudeDT);
      return dadosAtualizados;
    } else {
      // Se não existe, cria
      const dadosSalvos = await criarHistoricoSaudeRepository(historicoSaudeDT);
      return dadosSalvos;
    }
  } catch (e) {
    console.log("Erro ao editar histórico de saúde:", e);
    throw e;
  }
};

module.exports = { criarHistoricoSaudeService, editarHistoricoSaudeService };
