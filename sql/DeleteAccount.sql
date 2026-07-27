ALTER PROCEDURE [dbo].[sp_DeleteAccount]
	@Username VARCHAR(50)
AS
BEGIN
    -- Verificăm dacă există
    DECLARE @UserId INT;
    SELECT @UserId = ID FROM [User] WHERE Username = @Username;

    IF @UserId IS NULL
    BEGIN
        SELECT -1 AS Result; -- Utilizatorul nu există
    END
    ELSE
    BEGIN
        -- Începem o tranzacție pentru a fi siguri că se șterg ambele sau niciuna
        BEGIN TRANSACTION;
        
        BEGIN TRY
            -- 1. Ștergem întâi tranzacțiile (Copiii)
            DELETE FROM [Transaction] WHERE User_ID = @UserId;
            
            -- 2. Ștergem utilizatorul (Părintele)
            DELETE FROM [User] WHERE ID = @UserId;
            
            COMMIT TRANSACTION;
            SELECT 1 AS Result; -- Succes
        END TRY
        BEGIN CATCH
            ROLLBACK TRANSACTION;
            SELECT 0 AS Result; -- Eroare la ștergere
        END CATCH
    END
END
