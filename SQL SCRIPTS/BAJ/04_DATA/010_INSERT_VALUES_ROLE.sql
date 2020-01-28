IF NOT EXISTS ( SELECT * from [dbo].[Role] where [Code] = 'ADMIN')
begin
	INSERT INTO [dbo].[Role] ([Code],[Description],[RightId])
	VALUES ('ADMIN','Administrator',1)
end
IF NOT EXISTS ( SELECT * from [dbo].[Role] where [Code] = 'USER')
begin
	INSERT INTO [dbo].[Role] ([Code],[Description],[RightId])
	VALUES ('USER','User',2)
end