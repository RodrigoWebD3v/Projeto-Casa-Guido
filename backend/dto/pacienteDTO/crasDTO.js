const crasDTO = (objetoCras, pacienteId) => {
    return {
        pacienteId: pacienteId,
        municipio:  objetoCras.municipio,
        bairro:  objetoCras.bairro,
    }
};

module.exports = {
    crasDTO,
};