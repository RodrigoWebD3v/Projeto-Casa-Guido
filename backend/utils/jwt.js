const jwt = require('jsonwebtoken');

const JWT_SECRET = 'orgulho_casa_guido';

const JWT_EXPIRES_IN = '1h';

const REFRESH_TOKEN_EXPIRES_IN = '7d';

const gerarToken = (userId) => {
    return jwt.sign({ userId }, JWT_SECRET, { expiresIn: JWT_EXPIRES_IN });
};

const gerarRefresToken = (userId) => {
    return jwt.sign({ userId }, JWT_SECRET, { expiresIn: REFRESH_TOKEN_EXPIRES_IN });
};

const verificarToken = (token) => {
    return jwt.verify(token, JWT_SECRET);
};

module.exports = { gerarToken, gerarRefresToken, verificarToken };