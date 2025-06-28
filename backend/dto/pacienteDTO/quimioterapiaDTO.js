const quimioterapiaDTO = (objetoQuimioterapia, pacienteId) => {
    return {
        paciente: pacienteId,
        dataInicio:  objetoQuimioterapia.dataInicio,
        dataUltimaSessao:  objetoQuimioterapia.dataUltimaSessao,
        observacoes: objetoQuimioterapia.observacoes
    }
};

module.exports = {
    quimioterapiaDTO,
};