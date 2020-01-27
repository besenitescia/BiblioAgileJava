IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertCredential')
begin 
	DROP PROCEDURE [SP_InsertCredential]
end
GO
CREATE PROCEDURE [SP_InsertCredential]
(
	@BibliothequeId int
)
as
begin
	INSERT INTO [dbo].[Credential]	([BibliothequeId])
	values	(@BibliothequeId)

end
go