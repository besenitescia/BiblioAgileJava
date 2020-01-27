IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertLog')
begin 
	DROP PROCEDURE [SP_InsertLog]
end
GO
CREATE PROCEDURE [SP_InsertLog]
(
	@Type nvarchar(255) NULL
    ,@Level INT NULL
    ,@Date DATETIME NULL
    ,@Message nvarchar(255) NULL
)
as
begin
	INSERT INTO [dbo].[Log]
           ([Type]
           ,[Level]
           ,[Date]
           ,[Message])
     VALUES
           (@Type
           ,@Level
           ,@Date
           ,@Message)
end
go