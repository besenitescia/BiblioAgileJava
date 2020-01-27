IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetCredentials')
begin 
	DROP PROCEDURE [SP_GetCredentials]
end
GO
CREATE PROCEDURE [SP_GetCredentials]
as
begin
	SELECT [CredentialId]
      ,[BibliothequeId]
  FROM [dbo].[Credential]

end
go