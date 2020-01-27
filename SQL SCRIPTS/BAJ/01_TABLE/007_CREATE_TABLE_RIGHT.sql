IF EXISTS( select * from sysobjects s where s.name = 'Right')
begin
	drop table [Right]
end

CREATE TABLE [Right]
(
	RightId int primary key identity(1,1)
	,Edit bit null
	,[Read] bit null
	,[Create] bit null
	,[Delete] bit null
	,[Export] bit null
	,[Save] bit null
)