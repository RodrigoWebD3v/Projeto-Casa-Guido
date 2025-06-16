const pessoaDTO = (objetoPessoa, enderecoId) => {
    return {
        nome                    : objetoPessoa.nome,
        contato                 : objetoPessoa.contato,
        dataNascimento          : objetoPessoa.dataNascimento,
        cpf                     : objetoPessoa.cpf,  
        identidade              : objetoPessoa.identidade, 
        naturalidade            : objetoPessoa.naturalidade,
        genero                  : objetoPessoa.genero,
        escolaridade            : objetoPessoa.escolaridade,
        serie                   : objetoPessoa.serie,
        estadoCivil             : objetoPessoa.estadoCivil,
        situacaoProfissional    : objetoPessoa.situacaoProfissional,
        salario                 : objetoPessoa.salario,
        enderecoId              : enderecoId,
        telefone                : objetoPessoa.telefone,
        cartaoSus               : objetoPessoa.cartaoSus,
        profissao               : objetoPessoa.profissao,
    }
};

module.exports = {
    pessoaDTO,
};