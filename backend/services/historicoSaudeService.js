
const { historicoSaudeDTO } = require("../dto/pacienteDTO/historicoSaudeDTO");
const { criarHistoricoSaudeRepository } = require("../repository/historicoSaudeRepository");

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

module.exports = { criarHistoricoSaudeService };
