const events = [
  {
    img: "",
    nome: "evento 01",
    categoria: "marke",
    sobre: "loren loren loren",
    data: "12/15/68",
    hora: "20:54",
  },
  {
    img: "",
    nome: "evento 02",
    categoria: "tec",
    sobre: "loren loren lorenloren loren lorenloren loren lorenloren loren loren",
    data: "12/58/41",
    hora: "20:56",
  }
]

function pageHome(req, res) {
  return res.render("index.html")

}
function pageEvents(req, res) {
  const filters = req.query
  return res.render("event.html", { events, filters })

}
function pageCadastro(req, res) {
  return res.render("form.html")

}

const express = require('express')
const server =  express()

const nunjucks = require('nunjucks')
nunjucks.configure('src/views', {
  express: server,
  noCache: true,
})

server.post("/cadastro", () =>{
  console.log(req)
  res.json({
    "status": 200
  })
})

server.use(express.static("public"))
.get("/", pageHome)
.get("/cadastrar",pageCadastro)
.get("/pesquisar", pageEvents)
.listen(3000) 