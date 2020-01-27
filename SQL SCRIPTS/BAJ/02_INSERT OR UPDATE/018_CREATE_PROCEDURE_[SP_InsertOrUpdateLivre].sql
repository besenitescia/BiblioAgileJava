IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertOrUpdateLivre')
begin 
	DROP PROCEDURE [SP_InsertOrUpdateLivre]
end
GO
CREATE PROCEDURE [SP_InsertOrUpdateLivre]
(
	@LivreId int null
	,@Titre nvarchar(255) null
	,@Presentation nvarchar(255) null
	,@Parution int null
	,@Colonne int null
	,@Rangee int null
	,@Url nvarchar(max) null
	,@Etat nvarchar(255) null
	,@Responsable nvarchar(255) null
	,@BibliothequeId int null
	,@AuteurId int null
)
as
begin
	IF NOT EXISTS(SELECT * FROM [dbo].[Livre] WHERE [LivreId] = @LivreId)
	begin
		INSERT INTO [dbo].[Livre]	(Titre
									,Presentation
									,Parution
									,Colonne
									,Rangee
									,Url
									,Etat
									,Responsable
									,BibliothequeId
									,AuteurId)
		values	(@Titre
				,@Presentation
				,@Parution
				,@Colonne
				,@Rangee
				,@Url
				,@Etat
				,@Responsable
				,@BibliothequeId
				,@AuteurId)
	end
	ELSE
	BEGIN
		UPDATE [dbo].[Livre]
		SET  [Titre] = @Titre
			 ,[Presentation] = @Presentation
			 ,[Parution] = @Parution
			 ,[Colonne] = @Colonne
			 ,[Rangee] = @Rangee
			 ,[Url] = @Url
			 ,[Etat] = @Etat
			 ,[Responsable] = @Responsable
			 ,[BibliothequeId] = @BibliothequeId
			 ,[AuteurId] = @AuteurId
		WHERE [LivreId] = @LivreId
	END
end
go