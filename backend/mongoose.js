const mongoose = require('mongoose');
require('dotenv').config();


const MONGO_URI = process.env.MONGO_URI;
const DB_NAME = process.env.DB_NAME;

const connectMongoose = async () => {
  try {
    await mongoose.connect(MONGO_URI, {
      dbName: DB_NAME,
    });
    console.log('MongoDB conectado com sucesso!');
  } catch (err) {
    console.error('Erro ao conectar ao MongoDB:', err);
    process.exit(1);
  }
};

module.exports = connectMongoose; 