create database projetosambao
go
use projetosambao

create table escola (
id int identity not null primary key,
nome varchar(100),
total_nota decimal (3,1)
)

create table jurado (
id int identity not null primary key,
nome varchar(100)
)

create table quesito (
id int identity not null primary key,
nome varchar(100)
)

create table nota (
id_escola int foreign key references escola,
id_quesito int foreign key references quesito,
id_jurado int foreign key references jurado,
nota decimal (3,1)
primary key(id_escola, id_quesito, id_jurado)
)

insert into escola values
('Acadêmicos do Tatuapé', null),
('Rosas de Ouro', null),
('Mancha Verde', null), 
('Vai-Vai', null),
('X-9 Paulistana', null),
('Dragões da Real', null),
('Águia de Ouro', null),
('Nenê de Vila Matilde', null),
('Gaviões da Fiel', null),
('Mocidade Alegre', null),
('Tom Maior', null),
('Unidos de Vila Maria', null),
('Acadêmicos do Tucuruvi', null),
('Império de Casa Verde', null)

insert into quesito values
('Comissão de Frente'),
('Evolução'),
('Fantasia'),
('Bateria'),
('Alegoria'),
('Harmonia'),
('Samba-Enredo'),
('Mestre-Sala e Porta-Bandeira'),
('Enredo')

insert into jurado values
('1º Jurado'),
('2º Jurado'),
('3º Jurado'),
('4º Jurado'),
('5º Jurado')

/*

Consultas para eventuais problemas

select es.nome, nt.nota, rs.maior_nota, rs.menor_nota
from escola es
join nota nt
on es.id = nt.id_escola
join resultado rs
on rs.id_escola = nt.id_escola
join quesito qs
on qs.id = nt.id_quesito

select * from escola
select * from jurado
select * from quesito
select * from nota

select es.nome, rt.total_quesito
from escola es
join resultado rt
on es.id = rt.id_escola

select id from escola where nome = 'X-9 Paulistana'
*/

create procedure pc_sambao(@escola varchar(100),
	@jurado varchar(100),
	@quesito varchar(100),
	@nota decimal(3,1))
as
		declare @id_escola int,
				@id_jurado int,
				@id_quesito int
set @id_escola = (select id from escola where nome = @escola)
set @id_jurado = (select id from jurado where nome = @jurado)
set @id_quesito = (select id from quesito where nome = @quesito)
insert into nota values (@id_escola, @id_quesito, @id_jurado, @nota)




create function fn_total(@id_escola int)
returns decimal(4,1)
as
begin
declare @soma decimal(4,1),
	@nota decimal(4,1),
	@id_jurado int,
	@id_quesito int = 1,
	@menor decimal (3,1),
	@maior decimal(3,1)
	select @menor = cast(min(nota) as decimal(4,1)) from nota where id_escola = @id_escola
	select @maior = cast(max(nota) as decimal(4,1)) from nota where id_escola = @id_escola
	select @nota = cast(sum(nota) as decimal(4,1)) from nota where id_escola = @id_escola
	set @soma = @nota - (@menor + @maior)
return @soma
end


create procedure pc_verificaQ(@quesito varchar(100))
as
	declare
		@id_quesito int
set @id_quesito = (select id from quesito where nome = @quesito)
select el.nome as nome,qs.nome as nome_quesito, dbo.fn_separanota1(el.id, qs.id) as nota1,dbo.fn_separanota2(el.id, qs.id) as nota2,dbo.fn_separanota3(el.id, qs.id) as nota3,dbo.fn_separanota4(el.id, qs.id)as nota4,dbo.fn_separanota5(el.id, qs.id) as nota5, dbo.fn_maior(el.id, @id_quesito) as maior_descartada, dbo.fn_menor(el.id,@id_quesito) as menor_descarta,dbo.fn_total(el.id) as total
from quesito qs
inner join nota nt
on qs.id = nt.id_quesito
inner join escola el
on nt.id_escola = el.id
where nt.id_quesito = @id_quesito
group by el.nome, qs.nome, qs.id, el.id


create function fn_maior(@id_escola int, @id_quesito int)
returns decimal (3,1)
as
begin
declare 
@maior decimal(4,1)
select @maior = cast(max(nota) as decimal(4,1)) from nota where id_escola = @id_escola and id_quesito = @id_quesito
return @maior
end


create function fn_menor(@id_escola int, @id_quesito int)
returns decimal (4,1)
as
begin
declare 
@menor decimal(4,1)
select @menor = cast(min(nota) as decimal(4,1)) from nota where id_escola = @id_escola and id_quesito = @id_quesito
return @menor
end

create function fn_separanota1(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
		@nota1 decimal(4,1)
set @nota1 = (select nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 1)
return @nota1
end

create function fn_separanota2(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
		@nota2 decimal(4,1)
set @nota2 = (select nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 2)
return @nota2
end

create function fn_separanota3(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
		@nota3 decimal(4,1)
set @nota3 = (select nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 3)
return @nota3
end

create function fn_separanota4(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
		@nota4 decimal(4,1)
set @nota4 = (select nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 4)
return @nota4
end

create function fn_separanota5(@id_escola int, @id_quesito int)
returns decimal(4,1)
as
begin
declare
		@nota5 decimal(4,1)
set @nota5 = (select nota from nota where id_escola = @id_escola and id_quesito = @id_quesito and id_jurado = 5)
return @nota5
end
