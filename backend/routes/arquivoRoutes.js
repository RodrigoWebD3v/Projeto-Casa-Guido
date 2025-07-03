const express = require('express');
const multer = require('multer');
const path = require('path');

const upload = multer({
  storage: multer.diskStorage({
    destination: path.join(__dirname, '../uploads'),
    filename: (req, file, cb) => {
      cb(null, Date.now() + '-' + file.originalname);
    },
  }),
});

const router = express.Router();

router.post('/arquivos', upload.single('arquivo'), (req, res) => {
  if (!req.file) {
    return res.status(400).json({ message: 'Arquivo não enviado' });
  }
  res.status(200).json({ filename: req.file.filename });
});

module.exports = router;
