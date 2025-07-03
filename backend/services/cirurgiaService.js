const { criarCirurgiaRepository, atualizarCirurgia } = require("../repository/cirurgiaRepository");
const { cirurgiaDTO } = require("../dto/pacienteDTO/cirurgiaDTO");

const criarCirurgiaService = async (cirurgia, pacienteId) => {
  try {
    const cirurgiaDT = await cirurgiaDTO(cirurgia, pacienteId);
    const dadosSalvos = await criarCirurgiaRepository(cirurgiaDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

const editarCirurgiaService = async (cirurgiaId, cirurgia, pacienteId) => {
  try {
    const cirurgiaDT = await cirurgiaDTO(cirurgia, pacienteId);
    const dadosAtualizados = await atualizarCirurgia(cirurgiaId, cirurgiaDT);
    return dadosAtualizados;
  } catch (e) {
    console.log("Erro ao editar cirurgia:", e);
    throw e;
  }
};

module.exports = { criarCirurgiaService, editarCirurgiaService };
