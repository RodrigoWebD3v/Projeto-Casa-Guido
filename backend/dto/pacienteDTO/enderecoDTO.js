const enderecoDTO = (objetoEndereco) => {
    return {
        cep         : objetoEndereco.cep,
        logradouro  : objetoEndereco.logradouro,
        numero      : objetoEndereco.numero,
        complemento : objetoEndereco.complemento,  
        unidade     : objetoEndereco.unidade, 
        bairro      : objetoEndereco.bairro,
        localidade  : objetoEndereco.localidade,
        uf          : objetoEndereco.uf,
        estado      : objetoEndereco.estado,
        regiao      : objetoEndereco.regiao,
        referencia  : objetoEndereco.referencia,
        pais        : objetoEndereco.pais,
    }
};

module.exports = {
    enderecoDTO,
};