IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_GetUsers')
begin 
	DROP PROCEDURE [SP_GetUsers]
end
GO
CREATE PROCEDURE [SP_GetUsers]
as
begin
	SELECT	[UserId]
			,[Login]
			,[Password]
			,[Mail]
			,[Disable]
			,u.[RoleId]
			,[Code]
			,[Description]
			,ri.[RightId]
			,[Edit]
			,[Read]
			,[Create]
			,[Delete]
			,[Export]
			,[Save]
			,u.CredentialId
	from [dbo].[User] u
	inner join [dbo].[Credential] C
		on u.CredentialId = C.CredentialId
	inner join [dbo].[Role] r
		inner join [dbo].[Right] ri
			on r.RightId = ri.RightId
		on u.[RoleId] = r.[RoleId]
end
go