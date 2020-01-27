IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetAuteurs')
begin 
	DROP PROCEDURE [SP_GetAuteurs]
end
GO
CREATE PROCEDURE [SP_GetAuteurs]
as
begin
	SELECT [AuteurId]
      ,[Prenom]
      ,[Nom]
  FROM [dbo].[Auteur]
end
go