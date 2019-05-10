import java.util.*;


class Game{
public static void main(String[] args){
//Plan out this next
   //1. Set up Board, Rooms, Players
   //2. while not end of game:
      //while Player's turn
         //check which options are available (move, act, rehearse, upgrade, end turn, display info/score
         //(A)list options
         //take input
         //make sure input is valid
            //if move:
               //check which moves are available
               //display available moves with option to go back to (A)
               //take input
               //check input
                  //if input is invalid, ask again
               //move player
            //if upgrade:
               //list upgrades and their costs, give option to return to (A)
               //take input 
               //check input
                  //if input is invalid, ask again
               //upgrade player
            //if act:
               //if player has a role:
                  //wait for player input to roll dice
                  //roll dice and determine success/failure
                  //if success:
                     //pay player
                     //tell player that they won and display earnings
                     //if scene is finished:
                        //remove rehearse tokens from all players on the scene
                        //if there are leading actors:
                           //tell player scene is over and that it is time for bonus roll
                           //wait on player input to roll dice
                           //roll dice and distribute winnings
                           //tell players what they won
                        //else tell the player that the scene is over
                  //if fail:
                     //tell player that they fail
               //if player does not have a role:
                  //(B)find and list available roles
                  //take input
                  //if input is valid:
                     //player gets role
                  //else return to (B)
            //if rehearse:
               //add a token to the player
            //if display info:
               //list each player's money, rank, credits, etc.
               //list info about the board
            //if end turn
               //go to next player's turn
         //check for end of day
            //if end of day, execute end of day operations and move to next player
      //then go to next player
   //3. Display end of game information 
}
int TotalPlayers;
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

class Player{
static int rank = 1;
static int tokens = 0;
static String role;
//id of player
static int PlayerNum;
static int position;
static int money = 0;
static int credits = 0;
boolean LeadingRole;
   private static void Move(int position){
   }
   private static void Act(int tokens){
   }
   private static void Rehearse(){
      tokens++;
   }
   private static void Upgrade(int money, int credits, int rank, int desired_rank, int money_or_credits){
   //money_or_credits is so we know whic they want to pay with. 0 for money, 1 for credits
         if(desired_rank < rank){
            //send message that you can't upgrade to a lower rank
         }
         if(money_or_credits != 0 && money_or_credits != 1){
            //send message that this is not a valid option
         }
         //RANK TWO
         if(desired_rank == 2){
            if(money_or_credits == 0){
               if(money >= 4){
                  money = money - 4;
                  rank = 2;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
            if(money_or_credits == 1){
               if(credits >= 5){
                  credits = credits - 5;
                  rank = 2;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
         }
         //RANK THREE
         if(desired_rank == 3){
            if(money_or_credits == 0){
               if(money >= 10){
                  money = money - 10;
                  rank = 3;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
            if(money_or_credits == 1){
               if(credits >= 10){
                  credits = credits - 10;
                  rank = 3;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
         }
         //RANK FOUR
         if(desired_rank == 4){
            if(money_or_credits == 0){
               if(money >= 18){
                  money = money - 18;
                  rank = 4;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
            if(money_or_credits == 1){
               if(credits >= 15){
                  credits = credits - 15;
                  rank = 4;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
         }
         //RANK FIVE
         if(desired_rank == 5){
            if(money_or_credits == 0){
               if(money >= 28){
                  money = money - 28;
                  rank = 5;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
            if(money_or_credits == 1){
               if(credits >= 20){
                  credits = credits - 20;
                  rank = 5;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
         }
         //RANK SIX
         if(desired_rank == 6){
            if(money_or_credits == 0){
               if(money >= 40){
                  money = money - 40;
                  rank = 6;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
            if(money_or_credits == 1){
               if(credits >= 25){
                  credits = credits - 25;
                  rank = 6;
               }
               else{
                  //send message that they can't afford this upgrade
               }
            }
         }
   }
   
   private static int [] BonusRoll(int num_dice, int players[]){
      int [] winnings = new int [num_dice];
      Dice dice = new Dice();
      //store each roll(rolls one at a time)
      for(int i  = 0; i < num_dice; i++){
         winnings[i] = dice.RollDice();
      }
      //distribute among players
      Arrays.sort(winnings);
      return winnings;
   }
   private static void ClaimRole(){
   }
   private static void EndTurn(){
   }
   private static void AddMoney(int payout){
      money += payout;
   }
   private static void AddCredits(int payout){
      credits =+ payout;
   }
}
class Dice{
   public static int RollDice(){
      int roll = (int)(Math.random() * 6 + 1);
      return roll;
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