const { enderecoDTO } = require("../dto/pacienteDTO/enderecoDTO");
const { pessoaDTO } = require("../dto/pacienteDTO/pessoaDTO");
const { criarEnderecoService, editarEnderecoService } = require("./enderecoService");
const { criarPessoaRepository, atualizar, buscarPorId } = require("../repository/pessoaRepository");

const criarPessoaService = async (pessoa) => {
  try {
    // Verifica se os dados básicos da pessoa estão presentes
    if (!pessoa || !pessoa.nome) {
      throw new Error('Nome da pessoa é obrigatório');
    }

    const enderecoDT = await enderecoDTO(pessoa.endereco);
    const enderecoSalvo = await criarEnderecoService(enderecoDT);

    console.log("Salvou endereco");
    const pessoaDT = await pessoaDTO(pessoa, enderecoSalvo.id);
    const pessoaSalvo = await criarPessoaRepository(pessoaDT);

    return pessoaSalvo;
  } catch (e) {
    console.error("Erro ao criar pessoa:", e);
    throw e; // Re-throw para que o erro seja tratado adequadamente
  }
};

const editarPessoaService = async (idPessoa, pessoa) => {
  try {
    // Verifica se os dados básicos da pessoa estão presentes
    if (!pessoa || !pessoa.nome) {
      throw new Error('Nome da pessoa é obrigatório');
    }

    // Busca a pessoa existente para obter o ID do endereço
    const pessoaExistente = await buscarPorId(idPessoa);
    if (!pessoaExistente) {
      throw new Error('Pessoa não encontrada');
    }

    // Atualiza o endereço se fornecido
    let enderecoSalvo = null;
    if (pessoa.endereco && pessoaExistente.endereco) {
      const enderecoDT = await enderecoDTO(pessoa.endereco);
      enderecoSalvo = await editarEnderecoService(pessoaExistente.endereco, enderecoDT);
    } else if (pessoa.endereco) {
      // Se não tem endereço existente, cria um novo
      const enderecoDT = await enderecoDTO(pessoa.endereco);
      enderecoSalvo = await criarEnderecoService(enderecoDT);
    }

    const pessoaDT = await pessoaDTO(pessoa, enderecoSalvo ? enderecoSalvo.id : pessoaExistente.endereco);
    const pessoaAtualizada = await atualizar(idPessoa, pessoaDT);

    return pessoaAtualizada;
  } catch (e) {
    console.error("Erro ao editar pessoa:", e);
    throw e;
  }
};

module.exports = { criarPessoaService, editarPessoaService };
