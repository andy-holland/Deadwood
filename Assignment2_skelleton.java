import java.util.*;

class Player{
int rank;
int tokens;
String role;
int PlayerNum;
int position;
int money;
int credits;
boolean LeadingRole;
   private static void Move(int position){
   }
   private static void Act(int tokens){
   }
   private static void Reherse(){
   }
   private static void Upgrade(int money, int credits, int rank){
   }
   private static void BonusRoll(){
   }
   private static void ClaimRole(){
   }
   private static void EndTurn(){
   }
   private static void AddMoney(int payout){
   }
   private static void AddCredits(int payout){
   }
   private static void AddToken(){
   }
}

class Game{
int TotatPlayers;
int SceneCardTotal;
int DaysLeft;
LinkedList<Player> PlayerList = new LinkedList<Player>();
   private static void SetupBoard(){
   }
   private static void SetupCards(int IdNum, String SceneName, int NumOfRoles, int Budget){
   }
   private static Player SetupPlayers(int Playernum, int TotalPlayers){
   Player Player1 = new Player();
   return Player1;
   }
   private static boolean CheckRank(int PlayerRank){
   return true;
   }
   private static boolean CheckCastingOffice(){
   return true;
   }
   private static int CreditPayout(){
   return 0;
   }
   private static int MoneyPayout(){
   return 0;
   }
   private static int BonusPayout(){
   return 0;
   }
   private static void ResetDay(){
   }
   private static void EndGame(){
   }
}

class Dice{
int NumOfDice;
int SumOfDice;
   private static void RollDice(int NumOfDice){
   }
   private static void GiveSum(int SumOfDice){
   }
}

class Board{
int CastingOfficeNum;
int TrailersNum;
int Layout[] = new int[12];
   private static boolean LegalMove(int position, int destination){
   return true;
   }
   private static Room CreateRoom(int IdNum, String RoomName, int NumOfRoles, int ShotsLeft){
   Room Trailers = new Room();
   return Trailers;
   }
}

class Room{
int ShotsLeft;
int NumOfRoles;
int IdNum;
String RoomName;
int Connections[] = new int[4];
Role CharacterRole[] = new Role[4];
   private static boolean RoleTaken(){
   return true;
   }
   private static Role CreateRole(int Rank, String RoleName, String RoleQuote){
   Role NewRole = new Role();
   return NewRole;
   }
}

class Scenes extends Room{
int Budget;
}

class Role{
int Rank;
String RoleName;
String RoleQuote;
boolean Avalibility;
}