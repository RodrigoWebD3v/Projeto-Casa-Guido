const enderecoDTO = (objetoEndereco) => {
    return {
        cep : objetoEndereco.cep,
        logradouro : objetoEndereco.logradouro,
        complemento : objetoEndereco.complemento,
        bairro : objetoEndereco.bairro,
        cidade : objetoEndereco.localidade,
        estado : objetoEndereco.uf,
        referencia : objetoEndereco.referencia,
        numero : objetoEndereco.numero,
    }
}

module.exports =  { enderecoDTO };