IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetRights')
begin 
	DROP PROCEDURE [SP_GetRights]
end
GO
CREATE PROCEDURE [SP_GetRights]
as
begin
	SELECT [RightId]
      ,[Edit]
      ,[Read]
      ,[Create]
      ,[Delete]
      ,[Export]
      ,[Save]
  FROM [dbo].[Right]

end
go