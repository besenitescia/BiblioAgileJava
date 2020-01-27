IF EXISTS( select * from sysobjects s where s.name = 'Auteur')
begin
	drop table [Auteur]
end

CREATE TABLE [Auteur]
(
	AuteurId int primary key identity(1,1)
	,Prenom nvarchar(255)
	,Nom nvarchar(255)
)