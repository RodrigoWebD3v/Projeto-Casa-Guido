const { editarPacienteService } = require('../../services/editarPacienteService');
const AppError = require('../../handlerException/appError');

const editarPacienteController = async (req, res) => {
    try {
        const { id } = req.params;
        const dadosPaciente = req.body;

        if (!id) {
            throw new AppError('ID do paciente é obrigatório', 400);
        }

        const pacienteAtualizado = await editarPacienteService(id, dadosPaciente);
        
        res.status(200).json(pacienteAtualizado);
    } catch (error) {
        if (error instanceof AppError) {
            return res.status(error.statusCode).json({ error: error.message });
        }
        console.error('Erro ao editar paciente:', error);
        return res.status(500).json({ error: "Erro interno no servidor." });
    }
};

module.exports = { editarPacienteController }; 