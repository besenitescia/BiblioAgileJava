IF EXISTS( select * from sysobjects s where s.name = 'User')
begin
	drop table [User]
end

CREATE TABLE [User]
(
	UserId int primary key identity(1,1)
	,Login nvarchar(255)
	,Password nvarchar(255)
	,Mail nvarchar(255)
	,Disable bit
	,RoleId int
	,CredentialId int
)