# POO sambaSchool



Este projeto apresenta um crud completo, para que voce possa estar rodando ele em sua maquina  vai precisar de um banco de dados .
caso nao tenha um SGBD, pode estar baixando de graça o sql 2014 da microsoft neste endereço :  
https://www.microsoft.com/pt-br/download/details.aspx?id=42299

apos abrir  o projeto no eclipse,  tem uma pasta chamada "SQL", nesta pasta tem a query do sql, basta copiar esse query para o seu SGBD e 
executar, criando assim o banco para que o projeto funcione.

criado o banco  é necessario mudar suas permissoes de acesso, na pasta "SRC" no pacote "DAO" tem  uma classe chamda "GenericDao", 
esta é a classe de conexao, o nome padrao que  ao fazer download é "sambaSchool" para o banco, e "user","password" para usuario
e senha respectivamente. caso deseje mudar para seu proprio usuario e senha, é só alterar nesta classe.
 
 
 
-------------------------------------------------------------------------------------------
 Driver de conexao JTDS 1.3.1
 
https://sourceforge.net/projects/jtds/files/jtds/1.3.1/
