IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_DeleteLivre')
begin 
	DROP PROCEDURE [SP_DeleteLivre]
end
GO
CREATE PROCEDURE [SP_DeleteLivre]
(
	@LivreId int
)
as
begin
	DELETE FROM [dbo].[Livre]
	WHERE [LivreId] = @LivreId
end
go