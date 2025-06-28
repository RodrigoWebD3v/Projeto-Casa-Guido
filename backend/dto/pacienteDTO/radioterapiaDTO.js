const radioterapiaDTO = (objetoRadioterapia, pacienteId) => {
    return {
        paciente: pacienteId,
        dataInicio:  objetoRadioterapia.dataInicio,
        dataUltimaSessao:  objetoRadioterapia.dataUltimaSessao,
        observacoes: objetoRadioterapia.observacoes
    }
};

module.exports = {
    radioterapiaDTO,
};