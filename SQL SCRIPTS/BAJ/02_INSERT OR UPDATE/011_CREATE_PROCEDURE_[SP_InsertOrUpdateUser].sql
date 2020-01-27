IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'SP_InsertOrUpdateUser')
begin 
	DROP PROCEDURE [SP_InsertOrUpdateUser]
end
GO
CREATE PROCEDURE [SP_InsertOrUpdateUser]
(
	@userid int null,
	@login nvarchar(255) null,
	@password nvarchar(255) null,
	@mail nvarchar(255) null,
	@disable bit null,
	@roleid int null,
	@credentialid int null
)
as
begin
	IF NOT EXISTS(SELECT * FROM [dbo].[User] WHERE [UserId] = @userid)
	begin
		INSERT INTO [dbo].[User]	([Login]
									,[Password]
									,[Mail]
									,[Disable]
									,[RoleId]
									,[CredentialId])
		values	(@login
				,@password
				,@mail
				,@disable
				,@roleid
				,@credentialid)
	end
	ELSE
	BEGIN
		UPDATE [dbo].[User]
		SET  [Login] = @login
			,[Password] = @password
			,[Mail] = @mail
			,[Disable] = @disable
			,[RoleId] = @roleid
			,[CredentialId] = @credentialid
		WHERE [UserId] = @userid

	END
end
go