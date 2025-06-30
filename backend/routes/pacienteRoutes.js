const { Router } = require('express');

const { criarPacienteService } = require('../services/criarPacienteService');
const { editarPacienteService } = require('../services/editarPacienteService');

const router = Router();

router.post('/', async (req, res) => {
    try {
        console.log("CRIAR PACIENTE")
        const paciente = await criarPacienteService(req.body);
        res.status(201).json({
            data: paciente
        });
    } catch (error) {
        console.error('Error creating patient:', error);
        res.status(500).json({ message: error.message });
    }
});

router.put('/:id', () => {
    try {
        const paciente = editarPacienteService(req.params.id, req.body);
        res.status(200).json({
            data: paciente
        });
    } catch (error) {
        
    }
});

module.exports = router; 