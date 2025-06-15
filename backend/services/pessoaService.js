
const { enderecoDTO } = require("../dto/pacienteDTO/enderecoDTO");
const { pessoaDTO } = require("../dto/pacienteDTO/pessoaDTO");
const { criarEnderecoService } = require("./enderecoService");
const { criarPessoaRepository } = require("../repository/pessoaRepository");


const criarPessoaService = async (pessoa) => {
  try {
    const enderecoDT = await enderecoDTO(pessoa.endereco);
    const enderecoSalvo = await criarEnderecoService(enderecoDT);

    const pessoaDT = await pessoaDTO(pessoa, enderecoSalvo.id);
    const pessoaSalvo = await criarPessoaRepository(pessoaDT);

    return pessoaSalvo;
  } catch (e) {
    return e;
  }
};

module.exports = { criarPessoaService };
