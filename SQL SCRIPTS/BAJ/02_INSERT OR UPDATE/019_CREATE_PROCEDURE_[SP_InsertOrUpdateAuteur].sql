IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertOrUpdateAuteur')
begin 
	DROP PROCEDURE [SP_InsertOrUpdateAuteur]
end
GO
CREATE PROCEDURE [SP_InsertOrUpdateAuteur]
(
	@AuteurId int null,
	@Prenom nvarchar(255) null,
	@Nom nvarchar(255) null
)
as
begin
	IF NOT EXISTS(SELECT * FROM [dbo].[Auteur] WHERE [AuteurId] = @AuteurId)
	begin
		
		INSERT INTO [dbo].[Auteur]	([Prenom]
									,[Nom])
		values	(@Prenom
				,@Nom)
		set @AuteurId = SCOPE_IDENTITY()
		select @AuteurId
	end
	ELSE
	BEGIN
		UPDATE [dbo].[Auteur]
		SET  [Prenom] = @Prenom
			,[Nom] = @Nom
		WHERE [AuteurId] = @AuteurId

	END
end
go