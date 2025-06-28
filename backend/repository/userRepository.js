const User = require('../models/User');
const connectMongoose = require('../mongoose');

const criarUsuarioRepository = async (name, email, hashedPassword) => {
  try {
    await connectMongoose();
    const user = new User({
      name,
      email,
      password: hashedPassword
    });
    const save = await user.save();
    return save;
  } catch (err) {
    console.log(err);
    throw err;
  }
};

const buscarUsuarioPorEmail = async (email) => {
  await connectMongoose();
  return await User.findOne({ email });
};

const buscarUsuarioPorId = async (id) => {
  await connectMongoose();
  return await User.findById(id);
};

module.exports = { criarUsuarioRepository, buscarUsuarioPorEmail, buscarUsuarioPorId };