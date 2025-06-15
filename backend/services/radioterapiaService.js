
const { radioterapiaDTO } = require("../dto/pacienteDTO/radioterapiaDTO");
const { criarRadioterapiaRepository } = require("../repository/radioterapiaRepository");


const criarRadioterapiaService = async (radioterapia) => {
  try {
    const radioterapiaDT = await radioterapiaDTO(radioterapia);
    const dadosSalvos = await criarRadioterapiaRepository(radioterapiaDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarRadioterapiaService };
