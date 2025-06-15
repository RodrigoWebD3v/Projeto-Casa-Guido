
const { crasDTO } = require("../dto/pacienteDTO/crasDTO");
const { criarCrasRepository } = require("../repository/crasRepository");


const criarCrasService = async (cras) => {
  try {
    const crasDT = await crasDTO(cras);
    const dadosSalvos = await criarCrasRepository(crasDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarCrasService };
