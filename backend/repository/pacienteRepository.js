const Paciente = require('../models/Paciente');

const criarPacienteRepository = async (data) => {
    const paciente = await new Paciente(data).save();
    return paciente;
};
const buscarPacientePorIdRepository = async (id) => Paciente.findById(id);
const listarPacientesRepository = async () => Paciente.find();
const editarPacienteRepository = async (id, data) => Paciente.findByIdAndUpdate(id, data, { new: true });
const deletarPacienteRepository = async (id) => Paciente.findByIdAndDelete(id);

module.exports = { criarPacienteRepository, buscarPacientePorIdRepository, listarPacientesRepository, editarPacienteRepository, deletarPacienteRepository };