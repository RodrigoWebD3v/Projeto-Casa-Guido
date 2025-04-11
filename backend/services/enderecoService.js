
const { enderecoDTO } = require('../dto/enderecoDTO');
const { criarEnderecoRepository } = require('../repository/enderecoRepository');

const criarEnderecoService = async (endereco) => {
    try{
        const enderecoDTOOB = enderecoDTO(endereco);
        const enderecoRetorno = await  criarEnderecoRepository(enderecoDTOOB);

        return enderecoRetorno;
    }catch(error) {
        throw new Error("Erro ao criar endereco");
    }
};

module.exports = { criarEnderecoService };