IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertOrUpdateAuteur')
begin 
	DROP PROCEDURE [SP_InsertOrUpdateAuteur]
end
GO
--exec [SP_InsertOrUpdateAuteur] 0,'Burak','Esen'
CREATE PROCEDURE [SP_InsertOrUpdateAuteur]
(
	@Prenom nvarchar(255) null,
	@Nom nvarchar(255) null,
	@AuteurId int null
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
	end
	ELSE
	BEGIN
		UPDATE [dbo].[Auteur]
		SET  [Prenom] = @Prenom
			,[Nom] = @Nom
		WHERE [AuteurId] = @AuteurId

	END

	select [AuteurId] 
	from [dbo].[Auteur]
	where [Nom] = @Nom
	and [Prenom] = @Prenom
end
go