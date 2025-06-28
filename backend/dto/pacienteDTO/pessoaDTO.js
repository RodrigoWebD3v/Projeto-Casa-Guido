const pessoaDTO = (objetoPessoa, enderecoId) => {
    return {
        nome                    : objetoPessoa.nome || null,
        contato                 : objetoPessoa.contato || null,
        dataNascimento          : objetoPessoa.dataNascimento || null,
        cpf                     : objetoPessoa.cpf || null,  
        identidade              : objetoPessoa.identidade || null, 
        naturalidade            : objetoPessoa.naturalidade || null,
        genero                  : objetoPessoa.genero || null,
        escolaridade            : objetoPessoa.escolaridade || null,
        serie                   : objetoPessoa.serie || null,
        estadoCivil             : objetoPessoa.estadoCivil || null,
        situacaoProfissional    : objetoPessoa.situacaoProfissional || null,
        salario                 : objetoPessoa.salario || null,
        enderecoId              : enderecoId || null,
        telefone                : objetoPessoa.telefone || null,
        cartaoSus               : objetoPessoa.cartaoSus || null,
        profissao               : objetoPessoa.profissao || null,
    }
};

module.exports = {
    pessoaDTO,
};