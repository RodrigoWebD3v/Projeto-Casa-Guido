const bcrypt = require('bcrypt');

const saltRounds = 10; //Salt é um embaralhador para que duas senhas mesmo que identicas gerem hashs diferentes

const hashPassword = async (password) => {
    return await bcrypt.hash(password, saltRounds);
  };

const comparaPassword = async (password, hash) => {
    return await bcrypt.compare(password, hash);
};

module.exports = { hashPassword, comparaPassword };