
const { situacaoHabitacionalDTO } = require("../dto/pacienteDTO/situacaoHabitacionalDTO");
const { criarSituacaoHabitacionalRepository } = require("../repository/situacaoHabitacionalRepository");


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

module.exports = { criarSituacaoHabitacionalService };
