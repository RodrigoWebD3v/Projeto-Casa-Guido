require('dotenv').config();
const connectMongoose = require('../mongoose');
const User = require('../models/User');

async function seed() {
  await connectMongoose();

  const user = new User({
    name: 'Usuário Admin',
    email: 'admin@admin.com',
    password: 'admin123' // Idealmente, use um hash de senha em produção
  });

  try {
    await user.save();
    console.log('Usuário criado com sucesso!');
  } catch (err) {
    console.error('Erro ao criar usuário:', err.message);
  } finally {
    process.exit();
  }
}

seed(); 