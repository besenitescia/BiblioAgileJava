IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetBibliotheques')
begin 
	DROP PROCEDURE [SP_GetBibliotheques]
end
GO
CREATE PROCEDURE [SP_GetBibliotheques]
as
begin
	SELECT [BibliothequeId],
			[Nom]
  FROM [dbo].[Bibliotheque]
end
go