const express = require('express');
const fs = require('fs');
const path = require('path');


const router = express.Router();

router.use(express.json({ limit: '50mb' }));

router.post('/', async (req, res) => {
  console.log("Chamou arquivos")
  const { fileName, fileData, pacienteId } = req.body;

  if (!fileName || !fileData || typeof fileData !== 'string') {
    return res.status(400).json({ message: 'fileName ou fileData ausentes ou inválidos' });
  }

  try {
    const buffer = Buffer.from(fileData, 'base64');

    const subfolder = path.join(__dirname, '../uploads', pacienteId);
    const filePath = path.join(subfolder, fileName);

    if (!fs.existsSync(subfolder)) {
      fs.mkdirSync(subfolder, { recursive: true });
      console.log(`Diretório criado: ${subfolder}`);
    }else{
      fs.rmSync(subfolder, { recursive: true, force: true });
      console.log(`Diretório deletado: ${subfolder}`);

      fs.mkdirSync(subfolder, { recursive: true });
      console.log(`Diretório criado: ${subfolder}`);
    }

    fs.writeFile(filePath, buffer, (err) => {
      if (err) {
        console.error('Erro ao salvar arquivo:', err);
        return res.status(500).json({ error: 'Erro ao salvar o arquivo' });
      }

      res.status(200).json({ message: 'Arquivo salvo com sucesso!', path: filePath });
    });
  } catch (err) {
    console.error('Erro ao converter base64:', err);
    res.status(400).json({ message: 'Erro ao processar base64' });
  }
});

module.exports = router;
