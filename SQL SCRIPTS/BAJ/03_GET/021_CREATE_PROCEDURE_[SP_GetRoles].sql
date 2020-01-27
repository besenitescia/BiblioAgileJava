IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetRoles')
begin 
	DROP PROCEDURE [SP_GetRoles]
end
GO
CREATE PROCEDURE [SP_GetRoles]
as
begin
	SELECT [RoleId]
      ,[Code]
      ,[Description]
	  ,[RightId]
  FROM [dbo].[Role]

end
go