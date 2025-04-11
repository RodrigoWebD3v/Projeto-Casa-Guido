const {
    criarPacienteService,
  } = require("../../services/criarPacienteService");
const AppError = require("../../handlerException/appError")
  
  
  
const criarPacienteController = async (req, res) => {
    try {
      const paciente = await criarPacienteService(req.body);
      res.status(200).json(paciente);
    } catch (error) {
      if (error instanceof AppError) {
        return res.status(error.statusCode).json({ error: error.message });
      }      
       return res.status(500).json({ error: "Erro interno no servidor." });
    }
};
  
  
module.exports = { criarPacienteController };
  