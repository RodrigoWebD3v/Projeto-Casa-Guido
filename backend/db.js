
const { Pool } = require('pg');

const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: 'casa-guido',
  password: '12345678',
  port: 5432, // padrão do PostgreSQL
});

module.exports = pool;
