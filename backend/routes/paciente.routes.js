const { Router } = require('express');

const { criarPacienteService } = require('../services/pacienteService/criarPacienteService');
const { escreverLog } = require('../utils/escreverLog');
const { buscarPacientesSemIdBackendService } = require('../services/pacienteService/buscarPacientesSemIdBackendService');
const { buscarPacientePorIdService, editarPacienteService } = require('../services/pacienteService/editarPacienteService');

const router = Router();

router.post('/', async (req, res) => {
    try {
        await escreverLog("Iniciando requisição POST /");
        
        const resultado = await criarPacienteService(req.body);

        await escreverLog("Paciente criado com sucesso");
        res.status(201).json(resultado);
    } catch (error) {
        await escreverLog(`Erro na rota POST /: ${error.message}`);
        res.status(error.statusCode || 500).json({ 
            success: false,
            message: error.message 
        });
    }
});

router.post('/sem-idbackend', async (req, res) => {
    try {
        await escreverLog("Iniciando requisição POST /sem-idbackend");
        
        const resultado = await buscarPacientesSemIdBackendService(req.body);
        
        await escreverLog(`Retornando ${resultado.count} pacientes sem idBackend`);
        
        res.status(200).json(resultado);
    } catch (error) {
        await escreverLog(`Erro na rota /sem-idbackend: ${error.message}`);
        res.status(error.statusCode || 500).json({ 
            success: false,
            message: error.message 
        });
    }
});

router.get('/:id', async (req, res) => {
    try {
        await escreverLog(`Iniciando requisição GET /${req.params.id}`);
        
        const resultado = await buscarPacientePorIdService(req.params.id);
        
        await escreverLog(`Paciente encontrado: ${req.params.id}`);
        res.status(200).json(resultado);
    } catch (error) {
        await escreverLog(`Erro na rota GET /${req.params.id}: ${error.message}`);
        res.status(error.statusCode || 500).json({ 
            success: false,
            message: error.message 
        });
    }
});

router.put('/:id', async (req, res) => {
    try {
        await escreverLog(`Iniciando requisição PUT /${req.params.id}`);
        
        const resultado = await editarPacienteService(req.params.id, req.body);

        await escreverLog(`Paciente atualizado: ${req.params.id}`);
        res.status(200).json(resultado);
    } catch (error) {
        await escreverLog(`Erro na rota PUT /${req.params.id}: ${error.message}`);
        res.status(error.statusCode || 500).json({ 
            success: false,
            message: error.message 
        });
    }
});

module.exports = router;
