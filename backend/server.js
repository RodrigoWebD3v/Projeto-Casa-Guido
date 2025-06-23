const app = require("./app");
const PORT = process.env.PORT || 3001;
const db = require('./db'); // certifique-se que o caminho está correto

app.listen(PORT, "0.0.0.0", () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});

app.get('/', (req, res) => {
  res.send('API funcionando na porta 3001!');
});

// Lista mock de pessoas  
let pessoas = [
  { id: 1, nome: 'João Silva', idade: 30, telefone: "9999-9999", cpf: '8482748220' },
  { id: 2, nome: 'Maria Souza', idade: 25 , telefone: "9999-9999", cpf: '8482748220' }
];

// Rota para obter a lista de pessoas (mock)
// app.get('/api/pessoas', (req, res) => {
//   res.json(pessoas);
// });

app.get('/api/pessoas', async (req, res) => {
  try {
    const resultado = await db.query('SELECT * FROM pessoas'); // <- alterado para a tabela correta
    res.json(resultado.rows);
  } catch (error) {
    console.error("Erro ao buscar pessoas:", error);
    res.status(500).json({ erro: "Erro ao buscar pessoas" });
  }
});
