const cirurgiaDTO = (objetoCirurgia, pacienteId) => {
    return {
        paciente: pacienteId,
        nome:  objetoCirurgia.nome,
        data:  objetoCirurgia.data,
        cid :  objetoCirurgia.cid,
        observacoes: objetoCirurgia.observacoes
    }
};

module.exports = {
    cirurgiaDTO,
};