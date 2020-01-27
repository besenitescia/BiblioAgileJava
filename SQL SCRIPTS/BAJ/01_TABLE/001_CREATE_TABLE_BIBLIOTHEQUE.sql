IF EXISTS( select * from sysobjects s where s.name = 'Bibliotheque')
begin
	drop table [Bibliotheque]
end

CREATE TABLE [Bibliotheque]
(
	BibliothequeId int primary key identity(1,1)
	,Nom nvarchar(255)
)