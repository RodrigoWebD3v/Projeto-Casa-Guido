const Paciente = require("../../models/Paciente");
const Cirurgia = require("../../models/Cirurgia");
const Quimioterapia = require("../../models/Quimioterapia");
const RadioTerapia = require("../../models/RadioTerapia");
const AppError = require("../../handlerException/appError");

const buscarPacienteController = async (req, res) => {
    try {
        const { id } = req.params;
        
        // Busca o paciente
        const paciente = await Paciente.findById(id)
            .populate('pessoa')
            .populate('responsavel')
            .populate('conjugeResponsavel')
            .populate('outroResponsavel')
            .populate('ubs')
            .populate('cras');

        if (!paciente) {
            throw new AppError('Paciente não encontrado', 404);
        }

        // Busca as cirurgias do paciente
        const cirurgias = await Cirurgia.find({ paciente: id });
        
        // Busca as quimioterapias do paciente
        const quimios = await Quimioterapia.find({ paciente: id });
        
        // Busca as radioterapias do paciente
        const radios = await RadioTerapia.find({ paciente: id });

        // Monta o objeto de resposta com todas as informações
        const pacienteCompleto = {
            ...paciente.toObject(),
            cirurgias,
            quimios,
            radios
        };

        res.status(200).json(pacienteCompleto);
    } catch (error) {
        if (error instanceof AppError) {
            return res.status(error.statusCode).json({ error: error.message });
        }
        console.error('Erro ao buscar paciente:', error);
        return res.status(500).json({ error: "Erro interno no servidor." });
    }
};

module.exports = { buscarPacienteController }; 