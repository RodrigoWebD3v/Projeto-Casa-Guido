const express = require('express');
const router = express.Router();
const {
  registerUser,
  loginUser,
  refreshTokenFunc
} = require('../services/authServices');

router.post('/register', async (req, res) => {
  try {
    const { name, email, password } = req.body;
    await registerUser(name, email, password);
    const tokens = await loginUser(email, password);
    res.status(201).json(tokens);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

router.post('/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    const tokens = await loginUser(email, password);
    res.status(200).json(tokens);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

router.post('/refresh', async (req, res) => {
  try {
    const { refreshToken } = req.body;
    const tokens = await refreshTokenFunc(refreshToken);
    res.status(200).json(tokens);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

module.exports = router;