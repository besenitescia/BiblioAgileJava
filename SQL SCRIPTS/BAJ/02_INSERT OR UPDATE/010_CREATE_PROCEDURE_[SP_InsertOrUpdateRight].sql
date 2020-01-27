IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertOrUpdateRight')
begin 
	DROP PROCEDURE [SP_InsertOrUpdateRight]
end
GO
CREATE PROCEDURE [SP_InsertOrUpdateRight]
(
	@rightid int null
	,@edit bit null
	,@read bit null
	,@create bit null
	,@delete bit null
	,@export bit null
	,@save bit null
)
as
begin
	IF NOT EXISTS(SELECT * FROM [dbo].[Right] WHERE [RightId] = @rightid)
	begin
		INSERT INTO [dbo].[Right]
           ([Edit]
           ,[Read]
           ,[Create]
           ,[Delete]
           ,[Export]
           ,[Save])
		 VALUES
           (@edit
		   ,@read
		   ,@create
		   ,@delete
		   ,@export
		   ,@save)
	end
	else
	begin
		UPDATE [dbo].[Right]
		SET [Edit] = @edit
		  ,[Read] = @read
		  ,[Create] = @create
		  ,[Delete] = @delete
		  ,[Export] = @export
		  ,[Save] = @save
		WHERE [RightId] = @rightid
	end
end
go