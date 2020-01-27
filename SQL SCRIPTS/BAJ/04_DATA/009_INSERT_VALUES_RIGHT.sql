IF NOT EXISTS ( SELECT * from [dbo].[Right] where [RoleId] = '1')
begin
	INSERT INTO [dbo].[Right] ([Edit],[Read],[Create],[Delete],[Export],[Save],RoleId)
	VALUES (1, 1, 1, 1, 1, 1, 1)
end
IF NOT EXISTS ( SELECT * from [dbo].[Right] where [RoleId] = '2')
begin
	INSERT INTO [dbo].[Right] ([Edit],[Read],[Create],[Delete],[Export],[Save],RoleId)
	VALUES (0, 1, 0, 0, 1, 0, 2)
end