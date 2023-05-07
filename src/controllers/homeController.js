const path = require('path');

module.exports = {
  // Método que retorna a página HTML
  index: (req, res) => {
    res.sendFile(path.join(__dirname, '..', 'public', 'index.html'));
  }
}
