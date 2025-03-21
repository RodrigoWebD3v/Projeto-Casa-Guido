const express = require('express')
const cors = require('cors')
const app = express(cors())
const port = 3000

app.get('/', (req, res) => {
  res.send('Olá Mundo!')
})

app.listen(port, () => {
  console.log(`App de exemplo esta rodando na porta ${port}`)
})