const ubsDTO = (objetoUbs, pacienteId) => {
    return {
        pacienteId: pacienteId,
        municipio:  objetoUbs.municipio,
        bairro:  objetoUbs.bairro,
    }
};

module.exports = {
    ubsDTO,
};