
const { composicaoFamiliarDTO } = require("../dto/pacienteDTO/composicaoFamiliarDTO");
const { criarComposicaoFamiliarRepository } = require("../repository/composicaoFamiliarRepository");

const criarComposicaoFamiliar = async (composicaoFamiliar) => {
  try {
    const composicaoFamiliarDT = await composicaoFamiliarDTO(composicaoFamiliar);
    const dadosSalvos = await criarComposicaoFamiliarRepository(composicaoFamiliarDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarComposicaoFamiliar };
