const { situacaoHabitacionalDTO } = require("../dto/pacienteDTO/situacaoHabitacionalDTO");
const { criarSituacaoHabitacionalRepository, atualizarSituacaoHabitacional, buscarSituacaoHabitacionalPorPaciente } = require("../repository/situacaoHabitacionalRepository");


const criarSituacaoHabitacionalService = async (situacaoHabitacional,pacienteId) => {
  try {
    const situacaoHabitacionalDT = await situacaoHabitacionalDTO(situacaoHabitacional,pacienteId);
    const dadosSalvos = await criarSituacaoHabitacionalRepository(situacaoHabitacionalDT);
    return dadosSalvos;
  } catch (e) {
    console.log(e);
    return [];
  }
};

const editarSituacaoHabitacionalService = async (pacienteId, situacaoHabitacional) => {
  try {
    // Busca a situação habitacional existente do paciente
    const situacaoExistente = await buscarSituacaoHabitacionalPorPaciente(pacienteId);
    
    const situacaoHabitacionalDT = await situacaoHabitacionalDTO(situacaoHabitacional, pacienteId);
    
    if (situacaoExistente) {
      // Se existe, atualiza
      const dadosAtualizados = await atualizarSituacaoHabitacional(situacaoExistente._id, situacaoHabitacionalDT);
      return dadosAtualizados;
    } else {
      // Se não existe, cria
      const dadosSalvos = await criarSituacaoHabitacionalRepository(situacaoHabitacionalDT);
      return dadosSalvos;
    }
  } catch (e) {
    console.log("Erro ao editar situação habitacional:", e);
    throw e;
  }
};

module.exports = { criarSituacaoHabitacionalService, editarSituacaoHabitacionalService };
