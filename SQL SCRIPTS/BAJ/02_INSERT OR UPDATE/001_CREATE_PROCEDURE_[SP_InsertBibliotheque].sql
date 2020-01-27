IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertBibliotheque')
begin 
	DROP PROCEDURE [SP_InsertBibliotheque]
end
GO
CREATE PROCEDURE [SP_InsertBibliotheque]
(
	@nom nvarchar(255)
)
as
begin
	INSERT INTO [dbo].[Bibliotheque] ([Nom])
	values (@nom)
end
go