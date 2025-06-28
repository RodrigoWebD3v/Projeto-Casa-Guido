const { enderecoDTO } = require("../dto/pacienteDTO/enderecoDTO");
const { criarEnderecoRepository, atualizarEndereco } = require("../repository/enderecoRepository");

const criarEnderecoService = async (endereco) => {
  try {
    const enderecoDT = await enderecoDTO(endereco);
    const dadosSalvos = await criarEnderecoRepository(enderecoDT);
    return dadosSalvos;
  } catch (e) {
    console.error("Erro ao criar endereço:", e);
    throw e;
  }
};

const editarEnderecoService = async (idPessoa, endereco) => {
  try {
    const enderecoDT = await enderecoDTO(endereco);
    const dadosAtualizados = await atualizarEndereco(idPessoa, enderecoDT);
    return dadosAtualizados;
  } catch (e) {
    console.error("Erro ao editar endereço:", e);
    throw e;
  }
};

module.exports = { criarEnderecoService, editarEnderecoService };
