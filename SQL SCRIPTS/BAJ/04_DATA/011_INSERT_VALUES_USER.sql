USE [Baj]
GO

INSERT INTO [dbo].[User]
           ([Login]
           ,[Password]
           ,[Mail]
           ,[Disable]
           ,[RoleId]
           ,[CredentialId])
     VALUES
           ('admin101'
           ,'corruption!120'
           ,'burak.esen@edu.itescia.fr'
           ,0
           ,1
           ,1)
GO


