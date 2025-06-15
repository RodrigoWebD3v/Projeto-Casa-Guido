
const { criarCirurgiaRepository } = require("../repository/cirurgiaRepository");
const { cirurgiaDTO } = require("../dto/pacienteDTO/cirurgiaDTO");


const criarCirurgiaService = async (cirurgia) => {
  try {
    const cirurgiaDT = await cirurgiaDTO(cirurgia);
    const dadosSalvos = await criarCirurgiaRepository(cirurgiaDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarCirurgiaService };
