require('dotenv').config();
const express = require('express');
const cors = require('cors');
const { Pool } = require('pg');

const app = express();
const port = process.env.PORT || 3000;

// Configuração do banco de dados PostgreSQL
const pool = new Pool({
  user: process.env.DB_USER || "meu_usuario",
  host: process.env.DB_HOST || "localhost",
  database: process.env.DB_NAME || "meu_banco",
  password: process.env.DB_PASSWORD || "minha_senha",
  port: process.env.DB_PORT || 5432
});

// Middlewares
app.use(cors());
app.use(express.json());

// Rota de teste
app.get('/', async (req, res) => {
  res.json({ message: "API está rodando 🚀" });
});

// Rota para testar conexão com PostgreSQL
app.get('/db', async (req, res) => {
  try {
    const result = await pool.query('SELECT NOW()');
    res.json({ success: true, timestamp: result.rows[0] });
  } catch (error) {
    console.error("Erro ao conectar ao banco de dados:", error);
    res.status(500).json({ success: false, error: error.message });
  }
});

// Iniciar o servidor
app.listen(port, () => {
  console.log(`🚀 Servidor rodando em http://localhost:${port}`);
});
