const historicoSaudeDTO = (objetoHistoricoSaude, pacienteId) => {
    return {
        pacienteId: pacienteId,
        doencasFamilia:  objetoHistoricoSaude.doencasFamilia,
    }
};

module.exports = {
    historicoSaudeDTO,
};