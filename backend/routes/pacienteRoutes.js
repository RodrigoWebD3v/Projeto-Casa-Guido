const express = require('express');
const { criarPacienteService } = require('../services/criarPacienteService');

const pacienteRouter = express.Router();

pacienteRouter.post('/pacientes', async (req, res) => {
    try {
        const paciente = await criarPacienteService(req.body);
        res.status(201).json(paciente);
    } catch (error) {
        console.error('Error creating patient:', error);
        res.status(500).json({ message: error.message });
    }
});

module.exports = pacienteRouter; 