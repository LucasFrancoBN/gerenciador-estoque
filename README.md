# 📦 Gerenciador de Estoque 📦

<div align="center">
 <h2> Sumário</h2>
  <a href="#descrição-do-projeto">Descrição do projeto</a> -
  <a href="#ferramentas-utilizadas">Ferramentas utilizadas</a> - 
  <a href="#guia-de-implantação">Guia de implantação</a> -
  <a href="#desenvolvedores">Desenvolvedores</a>
</div>

## Descrição do projeto
<p align="justify">
Este projeto foi desenvolvido como uma aplicação de gerenciamento de estoque, proporcionando-me a oportunidade de aprofundar meus estudos em Kotlin, aliado ao framework Spring e suas extensões, como o Spring Security, Spring Data JPA, entre outros.
</p>

## Funcionalidades

`Funcionalidade 1:` CRUD de produtos.

`Funcionalidade 2:` Autorização utilizando OAuth2 e fluxo Authorization Code.

`Funcionalidade 3:` Customização do Token JWT

`Funcionalidade 4:` Além da implementação de um access token, foi feito a implementação de um Refresh token.

## Ferramentas utilizadas
<div style="display: flex; gap: 15px">
<a href="https://kotlinlang.org" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/kotlin/kotlin-original.svg" alt="Kotlin" width="40" height="40"/> 
</a>

<a href="https://spring.io/" target="_blank"> 
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring" width="40" height="40"/> 
</a>

<a href="https://www.postman.com/" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" alt="Postman" width="40" /> 
</a>

<a href="https://www.postgresql.org/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-plain.svg" width="40"/>
</a>

<a href="https://www.docker.com/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-plain.svg" width="40"/>
</a>

</div>

## Guia de implantação
Antes de iniciar o projeto, precisamos do [Docker](https://www.docker.com/) e [Git](https://git-scm.com/) instalados em nossas máquinas.
Tendo o git instlado, rode o seguinte comando no terminal da sua máquina:
``` bash
git clone https://github.com/LucasFrancoBN/gerenciador-estoque.git
```
Após isso, rode o seguinte comando no diretório raiz do projeto (somente se tiver o docker instalado em sua máquina): 
``` bash
docker-compose up
```

PS: Por questões de segurança, a autenticação/autorização com o google não estará disponível para testar, mas ainda será possível se autenticar via fluxo Authorization Code

## Desenvolvedores
<table align="center">
  <tr>
    <td align="center">
      <div>
        <img src="https://avatars.githubusercontent.com/LucasFrancoBN" width="120px;" alt="Foto no GitHub" class="profile"/><br>
          <b> Lucas Franco   </b><br>
            <a href="https://www.linkedin.com/in/lucas-franco-barbosa-navarro-a51937221/" alt="Linkedin"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" height="20"></a>
            <a href="https://github.com/LucasFrancoBN" alt="Github"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" height="20"></a>
      </div>
    </td>
  </tr>
</table>
