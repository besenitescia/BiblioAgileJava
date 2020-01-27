IF EXISTS( select * from sysobjects s where s.name = 'Livre')
begin
	drop table [Livre]
end

CREATE TABLE [Livre]
(
	LivreId int primary key identity(1,1)
	,Titre nvarchar(255)
	,Presentation nvarchar(255)
	,Parution int
	,Colonne int
	,Rangee int
	,Url nvarchar(max)
	,Etat nvarchar(255)
	,Responsable nvarchar(255) -- change type !
	,BibliothequeId int
	,AuteurId int
)