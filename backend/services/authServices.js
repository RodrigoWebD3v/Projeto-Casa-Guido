
const { hashPassword, comparaPassword } = require('../utils/bcrypt');
const { gerarToken, gerarRefresToken, verificarToken} = require('../utils/jwt');
const { criarUsuario, buscarUsuarioPorEmail, buscarUsuarioPorId } = require('../repository/userRepository');

const registerUser = async (name, email, password) => {
    const hashedPassword = await hashPassword(password);
    const user = await criarUsuario(name, email, hashedPassword);
    return user;
};

const loginUser = async (email, password) => {
    const user = await buscarUsuarioPorEmail(email);
    if (!user) throw new Error('User not found');
  
    const isValidPassword = await comparaPassword(password, user.password);
    if (!isValidPassword) throw new Error('Invalid password');
  
    const token = gerarToken(user.id);
    const refreshToken = gerarRefresToken(user.id);
  
    return { token, refreshToken };
};

const refreshTokenFunc = async (refreshToken) => {
    const decoded = verificarToken(refreshToken);
    const user = await  buscarUsuarioPorId(decoded.userId); 
    if (!user) throw new Error('User not found');
  
    const newToken = gerarToken(user.id);
    return { token: newToken };
};

module.exports = { registerUser, loginUser, refreshTokenFunc };