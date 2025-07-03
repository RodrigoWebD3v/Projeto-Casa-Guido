const enderecoDTO = (objetoEndereco) => {
    return {
        cep         : objetoEndereco.cep || null,
        logradouro  : objetoEndereco.logradouro || null,
        numero      : objetoEndereco.numero || null,
        complemento : objetoEndereco.complemento || null,  
        unidade     : objetoEndereco.unidade || null, 
        bairro      : objetoEndereco.bairro || null,
        localidade  : objetoEndereco.localidade || null,
        uf          : objetoEndereco.uf || null,
        estado      : objetoEndereco.estado || null,
        regiao      : objetoEndereco.regiao || null,
        referencia  : objetoEndereco.referencia || null,
        pais        : objetoEndereco.pais || null,
    }
};

module.exports = {
    enderecoDTO,
};