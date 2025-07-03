const { quimioterapiaDTO } = require("../dto/pacienteDTO/quimioterapiaDTO");
const { criarQuimioterapiaRepository, atualizarQuimioterapia } = require("../repository/quimioterapiaRepository");

const criarQuimioterapiaService = async (quimioterapia, pacienteId) => {
  try {
    const quimioterapiaDT = await quimioterapiaDTO(quimioterapia, pacienteId);
    const dadosSalvos = await criarQuimioterapiaRepository(quimioterapiaDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

const editarQuimioterapiaService = async (quimioterapiaId, quimioterapia, pacienteId) => {
  try {
    const quimioterapiaDT = await quimioterapiaDTO(quimioterapia, pacienteId);
    const dadosAtualizados = await atualizarQuimioterapia(quimioterapiaId, quimioterapiaDT);
    return dadosAtualizados;
  } catch (e) {
    console.log("Erro ao editar quimioterapia:", e);
    throw e;
  }
};

module.exports = { criarQuimioterapiaService, editarQuimioterapiaService };
