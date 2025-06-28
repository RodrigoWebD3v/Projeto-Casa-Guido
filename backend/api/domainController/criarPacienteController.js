const {
    criarPacienteService,
  } = require("../../services/criarPacienteService");
const AppError = require("../../handlerException/appError")
  

const criarPacienteController = async (req, res) => {
    try {
      const pacienteData = req.body; 
      const pacienteCriado = await criarPacienteService(pacienteData);
      res.status(201).json(pacienteCriado);
    } catch (error) {
      if (error instanceof AppError) {
        return res.status(error.statusCode).json({ error: error.message });
      }      
       return res.status(500).json({ error: "Erro interno no servidor." });
    }
};
  
  
module.exports = { criarPacienteController };
  