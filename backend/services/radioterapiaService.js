const { radioterapiaDTO } = require("../dto/pacienteDTO/radioterapiaDTO");
const { criarRadioTerapiaRepository, atualizarRadioterapia } = require("../repository/radioterapiaRepository");

const criarRadioterapiaService = async (radioterapia, pacienteId) => {
  try {
    const radioterapiaDT = await radioterapiaDTO(radioterapia, pacienteId);
    const dadosSalvos = await criarRadioTerapiaRepository(radioterapiaDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

const editarRadioterapiaService = async (radioterapiaId, radioterapia, pacienteId) => {
  try {
    const radioterapiaDT = await radioterapiaDTO(radioterapia, pacienteId);
    const dadosAtualizados = await atualizarRadioterapia(radioterapiaId, radioterapiaDT);
    return dadosAtualizados;
  } catch (e) {
    console.log("Erro ao editar radioterapia:", e);
    throw e;
  }
};

module.exports = { criarRadioterapiaService, editarRadioterapiaService };
