IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetUsersMail')
begin 
	DROP PROCEDURE [SP_GetUsersMail]
end
GO
CREATE PROCEDURE [SP_GetUsersMail]
as
begin
	SELECT [Mail]
	FROM [dbo].[User]
end
go