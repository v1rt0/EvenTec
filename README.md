<h2>Acabei de pegar o código e agora?</h2>

Só digitar o 'npm install' para instalar todas as dependencias e em seguida execute o 'npm start' e agora só codar.

<h3>Obs.:</h2>
<p>-A versão node utilizada é a 18.16.0 (a mais atual no momento que criei o projeto).</p>
<p>-NÃO FAÇA COMMIT NA MAIN. Novas features devem ser feitas uma branch diferente.</p>
-----------------------------------------------------------------------------------------------------------------------------------

A pasta public é para arquivos estáticos, como imagens, arquivos JavaScript e CSS. 

A pasta src é para o código do projeto, com as pastas controllers, models e views para o padrão MVC.

Por exemplo, os arquivos Sass que estão na pasta src/styles serão compilados para arquivos CSS que serão armazenados na pasta public/styles, e assim estarão disponíveis na internet para serem acessados pelo usuário comum.

Essa separação é importante porque ajuda a manter o projeto organizado e facilita o processo de build e deploy.

----------------------------------------------------------------------------------------------------------------------------------

Resumo da estruturação do projeto:

<ul>
  <li>node_modules/: pasta criada pelo npm, contendo as dependências do projeto;</li>
  <li>public/: pasta contendo os arquivos estáticos do projeto, como HTML, SCSS (CSS) e JS;</li>
  <li>public/index.html: arquivo HTML da página inicial (só um exemplo, pode ser alterado no futuro);</li>
  <li>public/scripts/: pasta contendo os arquivos JavaScript do projeto;</li>
  <li>public/styles/: pasta contendo os arquivos CSS do projeto;</li>
  <li>src/: pasta contendo os arquivos fonte do projeto;</li>
  <li>src/styles/: pasta contendo os arquivos Sass do projeto;</li>
  <li>src/styles/main.scss: arquivo principal Sass do projeto;</li>
  <li>src/controllers/: pasta contendo os arquivos JavaScript com as definições dos controllers do projeto;</li>
  <li>src/controllers/homeController.js: arquivo JavaScript com a definição do controller da página inicial;</li>
  <li>src/views/: pasta contendo os arquivos HTML das views do projeto;</li>
  <li>src/views/home/: pasta contendo os arquivos HTML da view da página inicial;</li>
  <li>src/views/home/index.html: arquivo HTML da view da página inicial;</li>
  <li>app.js: arquivo principal do projeto, contendo a configuração do servidor Express.js;</li>
  <li>package.json: arquivo contendo as informações e configurações do projeto;</li>
  <li>package-lock.json: arquivo gerado pelo npm para garantir a consistência das versões das dependências do projeto.</li>
</ul>

-----------------------------------------------------------------------------------------------------------------------------------
