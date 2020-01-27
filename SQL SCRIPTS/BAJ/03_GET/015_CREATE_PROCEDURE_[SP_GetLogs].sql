IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetLogs')
begin 
	DROP PROCEDURE [SP_GetLogs]
end
GO
CREATE PROCEDURE [SP_GetLogs]
as 
begin
	SELECT [LogId]
		  ,[Type]
		  ,[Level]
		  ,[Date]
		  ,[Message]
	  FROM [dbo].[Log]
end
GO


