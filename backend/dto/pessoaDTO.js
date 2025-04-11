const pessoaDTO = (objetoPaciente, endereco) => {
    return {
        nome : objetoPaciente.nome,
        data_nascimento : new Date(objetoPaciente.data_nascimento),
        cpf : objetoPaciente.cpf,
        identidade : objetoPaciente.identidade,
        naturalidade : objetoPaciente.naturalidade,
        genero : objetoPaciente.genero,
        escolaridade : objetoPaciente.escolaridade,
        contato : objetoPaciente.contato,
        enderecoId : endereco
    }
}

module.exports =  { pessoaDTO };