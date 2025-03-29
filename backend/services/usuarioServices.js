const { buscarUsuarioPorEmail } = require('../repository/userRepository');
const { consultaUsuarioDTO } = require('../dto/usuarioDTO');

const buscarUsuario = async (email) => {

    const user = await buscarUsuarioPorEmail(email);
    if (!user) throw new Error('User not found');

    userDTO = consultaUsuarioDTO(user);
  
    return { userDTO };
};

module.exports = { buscarUsuario };