const Paciente = require('../models/Paciente');

const criarPacienteRepository = async (data) => {
    const paciente = await new Paciente(data).save();
    return paciente;
};

const buscarPacientePorIdRepository = async (id) => Paciente.findById(id);

const listarPacientesRepository = async () => Paciente.find();

const buscarPacientesSemIdBackendRepository = async (filtroAdicional = null) => {
  // Filtro base para pacientes sem idBackend
  const filtroBase = { 
    $or: [ 
      { idBackend: { $exists: false } }, 
      { idBackend: null }, 
      { idBackend: '' } 
    ] 
  };

  // Se há filtro adicional, combinar com filtro base
  if (filtroAdicional) {
    return Paciente.find(filtroAdicional);
  }

  // Caso contrário, usar apenas o filtro base
  return Paciente.find(filtroBase);
};

const editarPacienteRepository = async (id, data) => Paciente.findByIdAndUpdate(id, data, { new: true });

const deletarPacienteRepository = async (id) => Paciente.findByIdAndDelete(id);

module.exports = { 
  criarPacienteRepository, 
  buscarPacientePorIdRepository, 
  listarPacientesRepository, 
  buscarPacientesSemIdBackendRepository, 
  editarPacienteRepository, 
  deletarPacienteRepository 
};
