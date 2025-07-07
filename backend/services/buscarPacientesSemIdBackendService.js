const { buscarPacientesSemIdBackendRepository } = require('../repository/pacienteRepository');

const buscarPacientesSemIdBackendService = async () => {
  return await buscarPacientesSemIdBackendRepository();
};

module.exports = { buscarPacientesSemIdBackendService };
