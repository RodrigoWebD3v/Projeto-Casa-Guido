const getArquivosResponseDTO = (nomeArquivo, conteudo ) => {
    return {
        nomeArquivo:nomeArquivo,
        conteudo: conteudo,
    }
};

module.exports = {
    getArquivosResponseDTO,
};