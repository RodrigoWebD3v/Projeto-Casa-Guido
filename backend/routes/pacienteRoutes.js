const { Router } = require('express');

const { criarPacienteService } = require('../services/criarPacienteService');
const { editarPacienteService , buscarPacientePorIdService} = require('../services/editarPacienteService');
const { escreverLog } = require('../utils/escreverLog');

const router = Router();


router.post('/', async (req, res) => {
    try {
        escreverLog("Criando paciente");
        console.log("CRIAR PACIENTE")
        const paciente = await criarPacienteService(req.body);

        escreverLog("Paciente criado com sucesso");
        res.status(201).json({
            data: paciente
        });
    } catch (error) {
        await escreverLog("Erro ao criar paciente" + error);
        res.status(500).json({ message: error.message });
    }
});

router.get('/:id', async (req, res) => {
    try {
        const pacientes = await buscarPacientePorIdService(req.params.id);
        res.status(200).json({
            data: pacientes
        });
    } catch (error) {}
})

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