const createPacienteResponseDTO = (objetoPaciente) => {
    return {
        nome:  objetoPaciente.pessoa.nome,
        id: objetoPaciente.id,
    }
};

module.exports = {
    createPacienteResponseDTO,
};