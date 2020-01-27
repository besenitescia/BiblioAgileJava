IF EXISTS( select * from sysobjects s where s.name = 'Role')
begin
	drop table [Role]
end

CREATE TABLE [Role]
(
	RoleId int primary key identity(1,1)
	,Code nvarchar(30)
	,Description nvarchar(255)
	,RightId int
)