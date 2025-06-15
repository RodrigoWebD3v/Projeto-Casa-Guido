
const { enderecoDTO } = require("../dto/pacienteDTO/enderecoDTO");
const { criarEnderecoRepository } = require("../repository/enderecoRepository");

const criarEnderecoService = async (endereco) => {
  try {
    const enderecoDT = await enderecoDTO(endereco);
    const dadosSalvos = await criarEnderecoRepository(enderecoDT);
    return dadosSalvos;
  } catch (e) {
    return [];
  }
};

module.exports = { criarEnderecoService };
