IF NOT EXISTS ( SELECT * from [dbo].[Right] where [RightId] = '1')
begin
	INSERT INTO [dbo].[Right] ([Edit],[Read],[Create],[Delete],[Export],[Save])
	VALUES (1, 1, 1, 1, 1, 1)
end
IF NOT EXISTS ( SELECT * from [dbo].[Right] where [RightId] = '2')
begin
	INSERT INTO [dbo].[Right] ([Edit],[Read],[Create],[Delete],[Export],[Save])
	VALUES (0, 1, 0, 0, 1, 0)
end