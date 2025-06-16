const composicaoFamiliarDTO = (objetoComposicaoFamiliar, pacienteId) => {
    return {
        pacienteId: pacienteId,
        parentesco  : objetoComposicaoFamiliar.parentesco,
        nome  : objetoComposicaoFamiliar.nome,
        data_nascimento  : objetoComposicaoFamiliar.data_nascimento,
        estudaOpc  : objetoComposicaoFamiliar.estudaOpc,
        escolaridade   : objetoComposicaoFamiliar.escolaridade,
        trabalhaOpc  : objetoComposicaoFamiliar.trabalhaOpc,
        renda  : objetoComposicaoFamiliar.renda,
        idade   : objetoComposicaoFamiliar.idade,
    }
};

module.exports = {
    composicaoFamiliarDTO,
};