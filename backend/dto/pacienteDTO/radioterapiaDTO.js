const radioterapiaDTO = (objetoRadioterapia, pacienteId) => {
    return {
        pacienteId: pacienteId,
        dataInicio:  objetoRadioterapia.dataInicio,
        dataUltimaSessao:  objetoRadioterapia.dataUltimaSessao,
    }
};

module.exports = {
    radioterapiaDTO,
};