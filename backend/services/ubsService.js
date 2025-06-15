
const { ubsDTO } = require("../dto/pacienteDTO/ubsDTO");
const { criarUbsRepository } = require("../repository/ubsRepository");


const criarUbsService = async (cras) => {
  try {
    const ubsDT = await ubsDTO(cras);
    const dadosSalvos = await criarUbsRepository(ubsDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarUbsService };
