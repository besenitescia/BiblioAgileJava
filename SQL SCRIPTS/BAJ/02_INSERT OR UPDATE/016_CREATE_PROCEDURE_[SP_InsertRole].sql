IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertRole')
begin 
	DROP PROCEDURE [SP_InsertRole]
end
GO
CREATE PROCEDURE [SP_InsertRole]
(
	@Code nvarchar(30) NULL
    ,@Description nvarchar(255) NULL
	,@RightId int null
)
as
begin
	INSERT INTO [dbo].[Role]
           ([Code]
		   ,[Description]
		   ,[RightId])
     VALUES
           (@Code
           ,@Description
		   ,@RightId)
end
go