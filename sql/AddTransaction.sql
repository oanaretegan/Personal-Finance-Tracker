ALTER PROCEDURE [dbo].[sp_AddTransaction]
    @Username VARCHAR(50),
    @Tip VARCHAR(11),
    @Suma DECIMAL(10,2),
    @Data DATE,
    @Locatie VARCHAR(100),
    @Categorie VARCHAR(100),
    @ModPlata VARCHAR(50)
AS
BEGIN
    DECLARE @UserId INT;
    SELECT @UserId = ID FROM [User] WHERE Username = @Username;

    INSERT INTO [Transaction](User_ID, Tip, Suma, [Data], Locatie, Categorie, ModPlata)
    VALUES(@UserId, @Tip, @Suma, @Data, @Locatie, @Categorie, @ModPlata);

    DECLARE @NewId INT = SCOPE_IDENTITY();

    IF @ModPlata = 'CASH'
    BEGIN
        IF @Tip = 'VENIT'
            UPDATE [User] SET SoldCash = SoldCash + @Suma WHERE Username = @Username;
        ELSE 
            UPDATE [User] SET SoldCash = SoldCash - @Suma WHERE Username = @Username;
    END
    ELSE
    BEGIN
        IF @Tip = 'VENIT'
            UPDATE [User] SET SoldCard = SoldCard + @Suma WHERE Username = @Username;
        ELSE 
            UPDATE [User] SET SoldCard = SoldCard - @Suma WHERE Username = @Username;
    END

    SELECT @NewId AS ID;
END