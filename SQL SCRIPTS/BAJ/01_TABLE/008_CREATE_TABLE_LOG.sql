IF EXISTS( select * from sysobjects s where s.name = 'Log')
begin
	drop table [Log]
end

CREATE TABLE [Log]
(
	LogId int primary key identity(1,1)
	,Type nvarchar(255)
	,Level int
	,Date datetime
	,Message nvarchar(255)
)