const express = require('express');
const router = express.Router();
const { buscarUsuario } = require('../services/usuarioServices');

router.post('/', async (req, res) => {
  try {
    const { email } = req.body;
    const usuario = await buscarUsuario(email);
    res.status(200).json(usuario);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

module.exports = router; 