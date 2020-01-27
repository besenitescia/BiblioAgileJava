IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetLivres')
begin 
	DROP PROCEDURE [SP_GetLivres]
end
GO
CREATE PROCEDURE [SP_GetLivres]
as
begin
	SELECT [LivreId]
		  ,[Titre]
		  ,[Presentation]
		  ,[Parution]
		  ,[Colonne]
		  ,[Rangee]
		  ,[Url]
		  ,[Etat]
		  ,[Responsable]
		  ,L.[BibliothequeId]
		  ,B.[Nom] as BNom
		  ,A.[AuteurId]
		  ,[Prenom]
		  ,a.[Nom]
	  FROM [dbo].[Livre] L
	  inner join [dbo].[Bibliotheque] B
		on L.BibliothequeId = B.BibliothequeId
	  inner join [dbo].[Auteur] A
		on L.AuteurId = A.AuteurId
end
go