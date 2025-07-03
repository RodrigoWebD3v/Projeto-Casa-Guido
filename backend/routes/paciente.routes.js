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

router.put('/:id', async (req, res) => {
    try {
        const paciente = await editarPacienteService(req.params.id, req.body);

        console.log(paciente)
        res.status(200).json({
            data: paciente
        });
    } catch (error) {
        console.error("Erro ao editar paciente:", error);
        res.status(500).json({ message: error.message });
    }
});

module.exports = router; 