
const { pessoaDTO } = require('../dto/pessoaDTO');
const { criarPessoa } = require('../repository/pessoaRepository');
const { criarEnderecoService } = require('../services/enderecoService');

const criarPacienteService = async (body) => {
        const endereco = await  criarEnderecoService(body.pessoa.endereco);
        const pessoa = pessoaDTO(body.pessoa, endereco.id);
        const pessoaretorno = await criarPessoa(pessoa);
        return pessoaretorno;
};

module.exports = { criarPacienteService };