import java.util.*;


class Game{
public static void main(String[] args){
   int SceneCardTotal;
   int DaysLeft = 4;
   int TotalPlayers;
   boolean EndTurn = false;
   Scanner read = new Scanner(System.in);
   System.out.println("Welcome to Deadwood!\n");
   System.out.println("How many players are there? You can have 2-8\n");
   TotalPlayers = read.nextInt();
   while(TotalPlayers < 2 || TotalPlayers > 8){   
      System.out.println("That is not a valid amount of players\n");
      System.out.println("How many players are there? You can have 2-8\n");
      TotalPlayers = read.nextInt();
   }
   System.out.println("Starting the game with "+TotalPlayers+" players\n");
   System.out.println("Press enter to start the game\n");
   read.nextLine();
   //1. Set up Board, Rooms, Players
   //players
   Player player1 = new Player();
   Player player2 = new Player();
   Player player3 = new Player();
   Player player4 = new Player();
   Player player5 = new Player();
   Player player6 = new Player();
   Player player7 = new Player();
   Player player8 = new Player();
   //2. while not end of game:
   while(DaysLeft > 0){
         //check which options are available (move, act, rehearse, upgrade, end turn, display info/score
         //(A)list options
         //take input
         //make sure input is valid

         //check for end of day
            //if end of day, execute end of day operations and move to next player
      //then go to next player
   }//<- end of daysleft while loop
   //close scanner
   read.close();
}
   private static void SetupBoard(){
   }
   private static void SetupCards(int IdNum, String SceneName, int NumOfRoles, int Budget){
   }
   //private static Player SetupPlayers(){
      //Player player = new Player();
      //return player;
   //}
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
static int instance;
int rank = 1;
static int tokens = 0;
String role;
//id of player
int PlayerNum;
int position;
static int money = 0;
static int credits = 0;
boolean LeadingRole;
   //constructor
   public Player(){
      instance += 1;
      PlayerNum = instance;
   }
   //method to run current player's turn
   public void PlayerTurn(){
      //open scanner 
      Scanner read = new Scanner(System.in);
      String input;
      System.out.println("Player"+PlayerNum+"'s turn. Your options are:\n");
      //list available options
      if(canMove() == true){
         System.out.println(" move ");
      }
      if(canAct() == true){
         System.out.println(" act ");
      }
      if(canUpgrade() == true){
         System.out.println(" upgrade ");
      }
      if(canRehearse() == true){
         System.out.println(" rehearse ");
      }
      System.out.println(" exit\n");
      //take input
      input = read.nextLine();
      while(input != "move" && input != "act" && input != "upgrade" && input != "rehearse" && input != "exit"){
         System.out.println("That is not acceptable input. Try again.");
         input = read.nextLine();
      }
      //close scanner
      read.close();
   }
   //these just return true for now but we will change that
   static boolean canMove(){
      return true;
   }
   private static boolean canAct(){
      return true;
   }
   private static boolean canUpgrade(){
      return true;
   }
   private static boolean canRehearse(){
      return true;
   }
   private static void GetInfo(){
               //list each player's money, rank, credits, etc.
               //list info about the board
   }
   private static void Move(int position){
               //if move:
               //check which moves are available
               //display available moves with option to go back to (A)
               //take input
               //check input
                  //if input is invalid, ask again
               //move player
   }
   private static void Act(int tokens){
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