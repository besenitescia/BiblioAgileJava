IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetLastInsertedAuteur')
begin 
	DROP PROCEDURE [SP_GetLastInsertedAuteur]
end
GO
CREATE PROCEDURE [SP_GetLastInsertedAuteur]

as
begin
	Select MAX([AuteurId]) as AuteurId
	From [dbo].[Auteur]
end
go