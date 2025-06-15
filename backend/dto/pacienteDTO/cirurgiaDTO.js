const cirurgiaDTO = (objetoCirurgia, pacienteId) => {
    return {
        pacienteId: pacienteId,
        nome:  objetoCirurgia.nome,
        data:  objetoCirurgia.data,
        cid :  objetoCirurgia.cid,
    }
};

module.exports = {
    cirurgiaDTO,
};