IF NOT EXISTS ( SELECT * from [dbo].[Role] where [Code] = 'ADMIN')
begin
	INSERT INTO [dbo].[Role] ([Code],[Description])
	VALUES ('ADMIN','Administrator')
end
IF NOT EXISTS ( SELECT * from [dbo].[Role] where [Code] = 'USER')
begin
	INSERT INTO [dbo].[Role] ([Code],[Description])
	VALUES ('USER','User')
end