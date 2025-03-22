const {
  registerUser,
  loginUser,
  refreshToken,
} = require("../services/authServices");

const registrar = async (req, res) => {
  try {
    const { name, email, password } = req.body;
    const user = await registerUser(name, email, password);
    res.status(201).json(user);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const login = async (req, res) => {
  try {
    const { email, password } = req.body;
    const tokens = await loginUser(email, password);
    res.status(200).json(tokens);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

const refresh = async (req, res) => {
  try {
    const { refreshToken } = req.body;
    const tokens = await refreshToken(refreshToken);
    res.status(200).json(tokens);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

module.exports = { registrar, login, refresh };
