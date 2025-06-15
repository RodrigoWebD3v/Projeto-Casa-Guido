const quimioterapiaDTO = (objetoQuimioterapia, pacienteId) => {
    return {
        pacienteId: pacienteId,
        dataInicio:  objetoQuimioterapia.dataInicio,
        dataUltimaSessao:  objetoQuimioterapia.dataUltimaSessao,
    }
};

module.exports = {
    quimioterapiaDTO,
};