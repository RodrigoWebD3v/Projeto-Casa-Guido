const app = require("./app");
const PORT = process.env.PORT || 3001;
const db = require('./db'); // certifique-se que o caminho está correto


app.listen(PORT, "0.0.0.0", () => {
  console.log(`Servidor rodando na porta ${PORT} Versão 1.0.0`);
});
