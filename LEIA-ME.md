Acabei de pegar o código e agora?

Só digitar o 'npm install' para instalar todas as dependencias e em seguida execute o 'npm start' e agora só codar.


Obs.:
    -A versão node utilizada é a 18.16.0 (a mais atual no momento que criei o projeto).
    -NÃO FAÇA COMMIT NA MAIN. Novas features devem ser feitas uma branch diferente.
-----------------------------------------------------------------------------------------------------------------------------------

A pasta public é para arquivos estáticos, como imagens, arquivos JavaScript e CSS. 

A pasta src é para o código do projeto, com as pastas controllers, models e views para o padrão MVC.

Por exemplo, os arquivos Sass que estão na pasta src/styles serão compilados para arquivos CSS que serão armazenados na pasta public/styles, e assim estarão disponíveis na internet para serem acessados pelo usuário comum.

Essa separação é importante porque ajuda a manter o projeto organizado e facilita o processo de build e deploy.

----------------------------------------------------------------------------------------------------------------------------------

Resumo da estruturação do projeto:

node_modules/: pasta criada pelo npm, contendo as dependências do projeto;
public/: pasta contendo os arquivos estáticos do projeto, como HTML, SCSS (CSS) e JS;
public/index.html: arquivo HTML da página inicial (só um exemplo, pode ser alterado no futuro);
public/scripts/: pasta contendo os arquivos JavaScript do projeto;
public/styles/: pasta contendo os arquivos CSS do projeto;
src/: pasta contendo os arquivos fonte do projeto;
src/styles/: pasta contendo os arquivos Sass do projeto;
src/styles/main.scss: arquivo principal Sass do projeto;
src/controllers/: pasta contendo os arquivos JavaScript com as definições dos controllers do projeto;
src/controllers/homeController.js: arquivo JavaScript com a definição do controller da página inicial;
src/views/: pasta contendo os arquivos HTML das views do projeto;
src/views/home/: pasta contendo os arquivos HTML da view da página inicial;
src/views/home/index.html: arquivo HTML da view da página inicial;
app.js: arquivo principal do projeto, contendo a configuração do servidor Express.js;
package.json: arquivo contendo as informações e configurações do projeto;
package-lock.json: arquivo gerado pelo npm para garantir a consistência das versões das dependências do projeto.

-----------------------------------------------------------------------------------------------------------------------------------
