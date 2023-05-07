const express = require('express');
const app = express();
const path = require('path');

// Define a porta para acessar o servidor
const PORT = process.env.PORT || 3000;

// Define o caminho para os arquivos estáticos
app.use(express.static(path.join(__dirname, 'public')));

// Define a rota para a página inicial
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'views', 'index.html'));
});

// Inicia o servidor
// Define a porta de escuta do servidor
app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});
