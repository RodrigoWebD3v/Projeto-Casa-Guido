
const { quimioterapiaDTO } = require("../dto/pacienteDTO/quimioterapiaDTO");
const { criarQuimioterapiaRepository } = require("../repository/quimioterapiaRepository");


const criarQuimioterapiaService = async (quimioterapia) => {
  try {
    const quimioterapiaDT = await quimioterapiaDTO(quimioterapia);
    const dadosSalvos = await criarQuimioterapiaRepository(quimioterapiaDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarQuimioterapiaService };
