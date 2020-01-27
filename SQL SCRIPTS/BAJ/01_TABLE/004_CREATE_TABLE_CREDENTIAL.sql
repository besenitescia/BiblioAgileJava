IF EXISTS( select * from sysobjects s where s.name = 'Credential')
begin
	drop table [Credential]
end

CREATE TABLE [Credential]
(
	CredentialId int primary key identity(1,1)
	,BibliothequeId int

)