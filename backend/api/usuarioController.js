const {
  buscarUsuario,
} = require("../services/usuarioServices.js");


const buscarUsuarioController = async (req, res) => {
  try {
    const { email } = req.body;
    const usuario = await buscarUsuario(email);
    console.log(usuario);
    res.status(200).json(usuario);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};


module.exports = { buscarUsuarioController };
