private class Player{
int rank;
int tokens;
String role;
int position;
int money;
int credits;
boolean LeadingRole;
private static void Move(int position);
private static void Act(int tokens);
private static void Reherse();
private static void Upgrade(int money, int credits, int rank);
private static void BonusRoll();
private static void ClaimRole();
private static void EndTurn();
private static void AddMoney(int payout);
private static void AddCredits(int payout);
private static void AddToken();
}

private class Game{
int TotatPlayers;
int SceneCardTotal;
int DaysLeft;
private static void SetupBoard();
private static void SetupCards();
private static void SetupPlayers();
private static boolean CheckRank();
private static boolean CheckCastingOffice();
private static int CreditPayout();
private static int MoneyPayout();
private static int BonusPayout();
private static void ResetDay();
private static void EndGame();
}

private class Dice{
int NumOfDice;
int SumOfDice;
private static void RollDice(int NumOfDice);
private static void GiveSum(int SumOfDice);
}

private class Scenes{
int Budget;
int NumOfRoles;
private static boolean RoleTaken();
}

private class Role{
int Rank;
boolean Avalibility;
}
private class Board{

}