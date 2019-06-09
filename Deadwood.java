import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class Deadwood{
   public static void main(String[] args){
   Game Deadwood = new Game();
   Deadwood.play();
   }    
}
class Game{
static GUI deadwood = new GUI();
static Player[] PlayerList;
static int tracker = 0;
static Board DefaultBoard = new Board();
static Scenes[] SceneDeck = new Scenes[40];
static int[] UsedCards = new int[40];
static int UsedCardIndex = 0;
static set[] SetList = new set[12];
static int SceneCardTotal;
static int DaysLeft = 4;
static int TotalPlayers;
static boolean EndTurn = false;
public void play(){
   //get total number of players
   boolean EndTurn = false;
   /*
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
   read.nextLine();
   */
   //create the board and populate sets with random cards
   SetList = SetupBoard();
   //create the frame for the game with the current boardstate
   SetupCards();
   DistrCards();
   deadwood.build();
   deadwood.CardBuild(SetList);
   deadwood.setVisible(true);
   Object[] playerOptions = {2,3,4,5,6,7,8};
   TotalPlayers = (int)JOptionPane.showInputDialog(deadwood, "How many players?","Before we start...",JOptionPane.PLAIN_MESSAGE,null,playerOptions,playerOptions[0]);
   //show the facedown cards in the frame
   PlayerList = new Player[TotalPlayers];
   //initialize players to trailer
   for(int p = 0; p < TotalPlayers; p++){
      Player Initial = Player.Builder(TotalPlayers);
      if(Initial != null){
         PlayerList[p] = Initial;
         PlayerList[p].UpdateLoc(SetList[10]);
         PlayerList[p].UpdateIcon(deadwood.pIcons[p]);
         deadwood.pIcons[p].setVisible(true);
      }
      //name players
      int translate = p+1;
      //System.out.println("What is the name of Player "+translate+"?\n");
      //String name = read.nextLine();
      PlayerList[p].UpdateName("Player "+translate);
   }
   if(TotalPlayers < 4){
      DaysLeft = 3;
   }
   if(TotalPlayers == 5){
      for(int i = 0; i < 5; i++){
         PlayerList[i].AddCredits(2);
      }
   }
   if(TotalPlayers == 6){
      for(int i = 0; i < 6; i++){
         PlayerList[i].AddCredits(4);
      }
   }
   if(TotalPlayers >= 7){
      for(int i = 0; i < TotalPlayers; i++){
         PlayerList[i].UpdateRank(2);
      }
   }
		
      //loop until day ends
		while(DaysLeft > 0){
         //change to a dialog message
			//System.out.println(PlayerList[tracker].GiveName()+": it is your turn");
			boolean endturn = false;
			while(endturn == false){
            /*while(input.equals("")){
            }
				if(input == "move"){
					Move(PlayerList[tracker]);
               input = "";
               //move needs to check to revel the card on the current set
               //and update the player location
				}
				else if(input == "upgrade"){
				   Upgrade(PlayerList[tracker]);
               //could have current rank displayed on 
				}
				else if(input == "rehearse"){
               PlayerList[tracker].Rehearse();
               PlayerList[tracker].resetMove();
					endturn = true;
				}
				else if(input == "claim role"){
               ClaimingRole(PlayerList[tracker]);
					endturn = true;
				}
				else if(input == "end turn"){
					endturn = true;
               PlayerList[tracker].resetMove();
				}
				else if(input == "act"){
               boolean Bonus = false;
               boolean SceneDone = Act(PlayerList[tracker], PlayerList[tracker].Act(PlayerList[tracker].GiveTokens()));
               if(SceneDone){
                  set CheckerSet = PlayerList[tracker].GivePosition();
                  Scenes CheckerCard = CheckerSet.returnScene();
                  part[] CheckerScene = CheckerCard.GiveParts();
                  for(int g = 0; g < 3; g++){
                     if(CheckerScene[g].ReturnTaken()){
                        Bonus = true;
                     }
                  }
                  if(Bonus){
                     BonusPayout(PlayerList[tracker], PlayerList);
                  }
                  for(int c = 0; c < TotalPlayers; c++){
                     if(PlayerList[c].GivePosition() == PlayerList[tracker].GivePosition()){
                        PlayerList[c].GivePart().UpdateTaken();
                        PlayerList[c].ResetPart();
                     }
                  } 
               }
				   endturn = true;
               PlayerList[tracker].resetMove();
				}*/
			}
         tracker ++;
         if(tracker == TotalPlayers){
            tracker = 0;
         }
         int DaySetter = 0;
         for(int d = 0; d < 12; d++){
            if(SetList[d].returnShots() > 0){
               DaySetter ++;
            }
         }
         if(DaySetter == 1){
            ResetDay(PlayerList);
         }
		}
      EndGame(PlayerList);
   //close scanner
   //read.close();
   } 
//populate board
   private static set[] SetupBoard(){
      DefaultBoard.getBoard();
      set[] SetList = new set[12];
      SetList = DefaultBoard.returnSetlist();
      return SetList;
   }
//populate cards
   private static void SetupCards(){
      for(int t = 0; t < 40; t++){
         Scenes Card = new Scenes();
         Card.getCards(t);
         SceneDeck[t] = Card;
         }
   }
//add cards to sets
   private static void DistrCards(){
   set[] SetList = new set[12];
   SetList = DefaultBoard.returnSetlist();
      for (int t = 0; t < 10; t++){
         boolean used = false;
         int cardNum = (int)(Math.random() * 40);
         if(UsedCardIndex != 0){
            for(int y = 0; y < UsedCardIndex-1; y++){
               if(UsedCards[y] == cardNum){
                  used = true;
               }
            }
            if (used == false){
            UsedCards[UsedCardIndex] = cardNum;
            UsedCardIndex ++; 
            }
         }
         else{
            UsedCards[UsedCardIndex] = cardNum;
            UsedCardIndex ++;
         }
         SetList[t].UpdateCard(SceneDeck[cardNum]);
      }
   }
   //check the preposed move and excecute
   public static void Move(Player player){
      boolean valid = false;
      set currentSet = player.GivePosition();
      String[] arr = player.GivePosition().returnNeighbors();
      Object[] neighbors;
      if(arr[3] == null){
         neighbors = new Object[3];
         for(int i = 0; i < 3; i++){
            neighbors[i] = arr[i];
         }
      }
      else{
         neighbors = new Object[4];
         neighbors = player.GivePosition().returnNeighbors();
      }
      String selection = (String)JOptionPane.showInputDialog(deadwood, "","Where would you like to move?",JOptionPane.PLAIN_MESSAGE,null,neighbors,neighbors[0]);
      for(int j = 0; j < 12; j++){
         if(SetList[j].returnName().equals(selection)){
            player.UpdateLoc(SetList[j]);
            deadwood.MovePlayerIcon(player, SetList[j]);
            Scenes current = SetList[j].returnScene();
            if(j < 10 && current.flipped == false){
               current.flipped = true;
               JLabel flip = SetList[j].returnCard();
               ImageIcon faceUp = new ImageIcon(current.GiveImage());
               flip.setIcon(faceUp);
            }
            break;
         }
      }
   }
//confirm the role player wants and update
   public static void ClaimingRole(Player player){
      part Role = player.ClaimRole(player);
      //if player hit exit
      if(Role == null){
         return;
      }
      Role.UpdateTaken();
      player.UpdateRole(Role);
      deadwood.PlayerRole(player, Role);
      /*if(CheckRank(player, Role)){
         Role.UpdateTaken();
         player.UpdateRole(Role);
      }
      else{
         System.out.println("you are not high enough rank for that role.");
         Role = player.ClaimRole();
      }*/
   }
//check the player's rank for the role
   public static boolean CheckRank(Player player, part part){
      boolean allowed = false;
      if(player.GiveRank() >= part.ReturnLevel()){
         allowed = true;
      }
      return allowed;
   }
//calculate if the player succeed on acting
   public static boolean Act(Player player, int DiceRoll){
      if(player.GivePosition().returnScene().GiveBudget() <= DiceRoll){
         Payout(player);
         player.GivePosition().ShotDone();
         JOptionPane.showMessageDialog(deadwood,"SUCCESS! total roll: "+DiceRoll+" needed: "+player.GivePosition().returnScene().GiveBudget());
      }
      else{
         Payout(player);
         JOptionPane.showMessageDialog(deadwood,"FAILURE... total roll: "+DiceRoll+" needed: "+player.GivePosition().returnScene().GiveBudget());
      }
      boolean SceneOver = false;
      if(player.GivePosition().returnShots() == 0){
         JOptionPane.showMessageDialog(deadwood,"Scene has been completed!");
         SceneOver = true;
      }
      return SceneOver;
   }
   //check to see if player is on office
   private static boolean CheckCastingOffice(Player player){
      boolean office = false;
      if(player.GivePosition() == SetList[11]){
         office = true;
      }
   return office;
   }
   //Check which upgrade player wants and update player
   public static void Upgrade(Player player){
      int input;
      int newRank;
      Scanner read = new Scanner(System.in);
      int money = player.GiveMoney();
      int credits = player.GiveCredits();
      int rank = player.GiveRank();
      System.out.println("Current rank: "+rank);
      System.out.println("Upgrades:");
      System.out.println("Rank 2: 4 dollars or 5 credits");
      System.out.println("Rank 3: 10 dollars or 10 credits");
      System.out.println("Rank 4: 18 dollars or 15 credits");
      System.out.println("Rank 5: 28 dollars or 20 credits");
      System.out.println("Rank 6: 40 dollars or 25 credits");
      System.out.println("input the number rank that you want");
      newRank = read.nextInt();
      while(rank > newRank){
         System.out.println("you cannot choose a rank lower than your current rank");
         newRank = read.nextInt();
      }
      System.out.println("press 1 to pay with money, press 2 to pay with credits, press 3 to exit upgrade");
      input = read.nextInt();
      while(input < 3){
         if(newRank == 2){
            if(input == 1){
               if(money - 4 < 0){
                  System.out.println("you cannot afford this upgrade with money, switch to credits or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddMoney(-4);
                  player.UpdateRank(2);
               }
            }
            else{
               if(credits - 5 < 0){
                  System.out.println("you cannot afford this upgrade with credits, switch to money or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddCredits(-5);
                  player.UpdateRank(2);
               }
            }
         }
         else if(newRank == 3){
            if(input == 1){
               if(money - 10 < 0){
                  System.out.println("you cannot afford this upgrade with money, switch to credits or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddMoney(-10);
                  player.UpdateRank(3);
               }
            }
            else{
               if(credits - 10 < 0){
                  System.out.println("you cannot afford this upgrade with credits, switch to money or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddCredits(-10);
                  player.UpdateRank(3);
               }
            }
         }
         else if(newRank == 4){
            if(input == 1){
               if(money - 18 < 0){
                  System.out.println("you cannot afford this upgrade with money, switch to credits or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddMoney(-18);
                  player.UpdateRank(4);
               }
            }
            else{
               if(credits - 15 < 0){
                  System.out.println("you cannot afford this upgrade with credits, switch to money or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddCredits(-15);
                  player.UpdateRank(4);
               }
            }
         }
         else if(newRank == 5){
            if(input == 1){
               if(money - 28 < 0){
                  System.out.println("you cannot afford this upgrade with money, switch to credits or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddMoney(-28);
                  player.UpdateRank(5);
               }
            }
            else{
               if(credits - 20 < 0){
                  System.out.println("you cannot afford this upgrade with credits, switch to money or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddCredits(-20);
                  player.UpdateRank(5);
               }
            }
         }
         else if(newRank == 6){
            if(input == 1){
               if(money - 40 < 0){
                  System.out.println("you cannot afford this upgrade with money, switch to credits or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddMoney(-40);
                  player.UpdateRank(6);
               }
            }
            else{
               if(credits - 25 < 0){
                  System.out.println("you cannot afford this upgrade with credits, switch to money or quit");
                  input = read.nextInt();
               }
               else{
                  player.AddCredits(-25);
                  player.UpdateRank(6);
               }
            }
         }
      }
   }
   //give money or credits to player
   public static void Payout(Player player){
   boolean card = player.GiveOnCard();
   if(card){
      player.AddCredits(2);
   }
   else{
      player.AddCredits(1);
      player.AddMoney(1);
   }
      
   }
   //give money to all players who get a bonus payout
   public static void BonusPayout(Player player, Player[] PlayerList){
      set currentSet = player.GivePosition();
      int diceNum = currentSet.returnScene().GiveBudget();
      int[] rolls = new int[diceNum];
      Player[] RoleLoc = new Player[6]; 
      for(int k = 0; k < diceNum; k++){
         Dice DiceB = new Dice();
         rolls[k] = DiceB.RollDice();
      }
      Arrays.sort(rolls);
      for(int y = 0; y < TotalPlayers; y++){
         if(PlayerList[y].GivePosition() == currentSet){
            if(PlayerList[y].GiveOnCard()){
               RoleLoc[PlayerList[y].GivePart().ReturnLevel()-1] = PlayerList[y];
            }
            else{
               PlayerList[y].AddMoney(PlayerList[y].GivePart().ReturnLevel());
            }
         }
      }
      int scroller = 5;
      while(diceNum != 0){
         if(RoleLoc[scroller] != null){
            RoleLoc[scroller].AddMoney(rolls[diceNum]);
            diceNum--;
         }
         if(scroller == 0){
            scroller = 5;
         }
      }
   }
   //reset the players and repopulate the board, reduce days
   private static void ResetDay(Player[] PlayerList){
      SetList = DefaultBoard.returnSetlist();
      DistrCards();
      for(int r = 0; r < TotalPlayers; r++){ 
         PlayerList[r].UpdateLoc(SetList[10]);
      }
      DaysLeft --;
   }
   //display the stats at the end of the game
   private static void EndGame(Player[] PlayerList){
      System.out.println("The Game is over! Go away!");
      for(int m = 0; m < TotalPlayers; m++){
         PlayerList[m].GetInfo();
         System.out.println("--------------------------");
      }
   }
}

class Player{
private static int instance;
private int rank = 1;
private int tokens = 0;
private String roleName;
//id of player
String PlayerName;
int PlayerNum;
private set currentposition = new set();
private part playerpart = new part();
private int money = 0;
private int credits = 0;
private boolean hasmoved = false;
private boolean hasUpgraded = false;
private JLabel playerIcon;
   //constructor
   private Player(){
      instance += 1;
      PlayerNum = instance;
   }
   static public Player Builder(int NumofPlayers){
      Player creation = null;
      if(instance <= NumofPlayers && instance <= 8){
         creation = new Player();
         return creation;
      }
      return creation;
   }
   //returning variables of player
   public void UpdateRoleName(String RoleName){
      roleName = RoleName;
   }
   public void UpdateRole(part role){
      playerpart = role;
   }
   public void UpdateLoc(set setname){
      currentposition = setname;
   }
   public void UpdateName(String name){
      PlayerName = name;
   }
   public void UpdateRank(int newRank){
      rank = newRank;
   }
   public void UpdateIcon(JLabel icon){
      playerIcon = icon;
   }
   public JLabel GiveIcon(){
      return playerIcon;
   }
   public String GiveName(){
      return PlayerName;
   }
   public int GiveRank(){
      return rank;
   }
   public set GivePosition(){
      return currentposition;
   }
   public part GivePart(){
      return playerpart;
   }
   public void ResetPart(){
      playerpart = new part();
   }
   public boolean GiveOnCard(){
      return playerpart.ReturnOnCard();
   }
   public int GiveTokens(){
      return tokens;
   }
   public void resetMove(){
      hasmoved = false;
   }
   //method to run current player's turn
   public String PlayerTurn(){
        //open scanner 
      Scanner read = new Scanner(System.in);
      String input;
      System.out.println("Player"+PlayerNum+"'s turn. Your options are:\n");
      
        //list available options
      if(!hasmoved){
         System.out.println(" move ");
      }
      
      if(playerpart.ReturnName() != null){
         System.out.println(" act (this will end your turn)");
      }
      
      if(canUpgrade(hasUpgraded) == true){
         System.out.println(" upgrade ");
      }
      
      if(playerpart.ReturnName() != null){
         System.out.println(" rehearse (this will end your turn)");
      }
      
      if(canClaim(playerpart.ReturnName() == null) && playerpart.ReturnName() == null){
      	System.out.println(" claim role (this will end your turn)");
      }
      System.out.println(" get info ");
      System.out.println(" end turn ");
      //take input
      input = read.nextLine();
      if(input.equals("move") && !hasmoved){
		   input = "move";
		   hasmoved = true;
	   }
	
   	else if(input.equals("act") && playerpart.ReturnName() != null){
   		input = "act";
   	}
   
   	else if(input.equals("upgrade") && canUpgrade(hasUpgraded)){
   		input = "upgrade";
   	}
   
   	else if(input.equals("rehearse") && playerpart.ReturnName() != null){
   		input = "rehearse";
   	}
   
   	else if(input.equals("end turn")){
   		input = "end turn";
   	}
   
   	else if(input.equals("claim role") && canClaim(playerpart.ReturnName() == null)){
   		input = "claim role";
   	}
      else if(input.equals("get info")){
         GetInfo();
      }
   	else{
   		System.out.println("invalid input");
   		input = null;
   	}
      return input;
   }
   //check if player is allowed to upgrade rank
   private boolean canUpgrade(boolean hasUpgraded){
      if(currentposition.returnName() == null){
         hasUpgraded = false;
      }
      if(currentposition.returnName().equals("office")){
        hasUpgraded = true;
      } 
      return hasUpgraded;
   }
   //check if player is allowed to claim a role
   private boolean canClaim(boolean OnRole){
      boolean allowed = false; 
      if(currentposition.returnName() == null){
         OnRole = true;
         return !OnRole;
      }
      part[] tempPartList = currentposition.returnParts();
      part[] tempScenePartList = currentposition.returnScene().GiveParts();
      if(tempPartList[0] == null){
         OnRole = true;
         return !OnRole;
      }
      for(int p = 0; p < 4; p++){
         if(!tempPartList[p].ReturnTaken()){
            allowed = true;
            return allowed;
         }
      }
      for(int q = 0; q < 3; q++){
         if(!tempScenePartList[q].ReturnTaken()){
            allowed = true;
            return allowed;
         }
      }
      return !OnRole; 
   }
   //print info on player
   public void GetInfo(){
      System.out.println("Player name: "+PlayerName);
      System.out.println("Rank: "+rank);
      System.out.println("money: "+money);
      System.out.println("credits: "+credits);
      System.out.println("tokens: "+tokens);
      System.out.println("set: "+currentposition.returnName());
      if(playerpart.ReturnName() != null){
         System.out.println("scene: "+currentposition.returnScene().GiveName());
         System.out.println("scene budget: "+currentposition.returnScene().GiveBudget());
         System.out.println("role: "+playerpart.ReturnName());
      }
   }
   //roll dice to act
   public int Act(int tokens){
      Dice dice = new Dice();
      return dice.RollDice() + tokens;
   }
   //add a token for rehearsing
   public void Rehearse(){
      tokens++;
   }
   //roll dice to get bonus payout
     public int [] BonusRoll(int num_dice, int players[]){
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
   public part ClaimRole(Player player){//gives game the role player wants
      Object[] playerOptions;
      part[] optionsB = currentposition.returnParts();
      part[] optionsS = currentposition.returnScene().GiveParts();
      String[] optionslist = new String[8];
      part[] optionsparts = new part[8];
      int optionsIndex = 1;
      int index = 0;
      for(int i = 0; i < 4; i++){
         if(optionsB[i] != null  && optionsB[i].ReturnTaken() == false && Game.CheckRank(player, optionsB[i])){
            optionsparts[i] = optionsB[i];
            optionslist[index] = optionsB[i].ReturnName();
            index++;
            optionsIndex ++;
         }
      }
      for(int i = 0; i < 3; i++){
         if(optionsS[i] != null && optionsS[i].ReturnTaken() == false && Game.CheckRank(player, optionsS[i])){
            optionsparts[i+4] = optionsS[i];
            optionslist[index] = optionsS[i].ReturnName();
            index++;
            optionsIndex ++;
         }
      }
      String[] optionslist2 = new String[optionsIndex];
      for(int i = 0; i < optionsIndex - 1; i++){
         optionslist2[i] = optionslist[i];
      }
      optionslist2[optionsIndex-1] = "exit";
      playerOptions = optionslist2;
      String rolestring = (String)JOptionPane.showInputDialog(Game.deadwood, "","Choose a Role",JOptionPane.PLAIN_MESSAGE,null,playerOptions,playerOptions[0]);
      if(rolestring.equals("exit")){
         return null;
      }
      part role;
      int slot = 10;
      for(int i = 0; i < 8; i ++){
         if(optionsparts[i] != null && rolestring.equals(optionsparts[i].ReturnName())){
            slot = i;
         }
      }
      if(slot == 10){
         System.out.println("ERROR: in Player.ClaimRole");
      }
      role = optionsparts[slot];
      return role;
   }
   //adjust variables of player
   public void AddMoney(int payout){
      money += payout;
   }
   public int GiveMoney(){
      return money;
   }
   public void AddCredits(int payout){
      credits += payout;
   }
   public int GiveCredits(){
      return credits;
   }
}
//dice
class Dice{
   public static int RollDice(){
      int roll = (int)(Math.random() * 6 + 1);
      return roll;
   }
}

class Board{
private DocHandler boardDoc = new DocHandler();
private Document docB;
private set[] SetList = new set[12];
//give the created setlist
   public set[] returnSetlist(){
      return SetList;
   }
   //get the document to build setlist
   public void getBoard(){
      try{
         docB = boardDoc.getDoc("board.xml");
         readBoard(docB);
      }
      catch(ParserConfigurationException ex){
         System.out.println("XML Parse Failure");
         ex.printStackTrace();
      }
   }
   //parse through the document and get the needed data
   public void readBoard(Document docB){
      Element root = docB.getDocumentElement();
      NodeList board = root.getElementsByTagName("set");
      NodeList trailer = root.getElementsByTagName("trailer");
      NodeList Office = root.getElementsByTagName("office");
      String TrailerNeighbors[] = new String[4];
      String OfficeNeighbors[] = new String[4];
      for(int t =0; t < board.getLength(); t++){
         Node set = board.item(t);
         //get the set name and update
         String setName = set.getAttributes().getNamedItem("name").getNodeValue();
         set Set = new set();
         Set.UpdateName(setName);
         NodeList inside = set.getChildNodes();
         for(int q = 0; q < inside.getLength(); q++){
            Node setData = inside.item(q);
            //get neighbors
            if("neighbors".equals(setData.getNodeName())){
               NodeList neighbors = setData.getChildNodes();
               for(int u = 1; u < neighbors.getLength(); u = u+2){
                  Node neighbor = neighbors.item(u);
                  String neighborName = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                  Set.AddNeighbor(neighborName);
               }
            }
            //get area of set
            else if("area".equals(setData.getNodeName())){
               int x = Integer.parseInt(setData.getAttributes().getNamedItem("x").getNodeValue());
               int y = Integer.parseInt(setData.getAttributes().getNamedItem("y").getNodeValue());
               int h = Integer.parseInt(setData.getAttributes().getNamedItem("h").getNodeValue());
               int w = Integer.parseInt(setData.getAttributes().getNamedItem("w").getNodeValue());
               Set.UpdateArea(x,y,h,w);
            }
            //get area and number of takes on set
            else if("takes".equals(setData.getNodeName())){
               NodeList takeList = setData.getChildNodes();
               for(int u = 1; u < takeList.getLength(); u = u+2){
                  Node takes = takeList.item(u);
                  int takeNum = Integer.parseInt(takes.getAttributes().getNamedItem("number").getNodeValue());
                  NodeList takeArea = takes.getChildNodes();
                  Node takeAreaAtt = takeArea.item(0);
                  int takex = Integer.parseInt(takeAreaAtt.getAttributes().getNamedItem("x").getNodeValue());               
                  int takey = Integer.parseInt(takeAreaAtt.getAttributes().getNamedItem("y").getNodeValue());
                  int takeh = Integer.parseInt(takeAreaAtt.getAttributes().getNamedItem("h").getNodeValue());
                  int takew = Integer.parseInt(takeAreaAtt.getAttributes().getNamedItem("w").getNodeValue());
                  Set.ShotsArea(takex, takey, takeh, takew);
               }
            }
            //get parts on set
            else if("parts".equals(setData.getNodeName())){
               NodeList parts = setData.getChildNodes();
               for(int u = 1; u < parts.getLength(); u = u+2){
                  Node part = parts.item(u);
                  String partName = part.getAttributes().getNamedItem("name").getNodeValue();
                  part SetPart = new part();
                  SetPart.UpdateName(partName);
                  int partLevel = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
                  SetPart.UpdateLevel(partLevel);
                  NodeList partContents = part.getChildNodes();
                  int partx = Integer.parseInt(partContents.item(1).getAttributes().getNamedItem("x").getNodeValue());               
                  int party = Integer.parseInt(partContents.item(1).getAttributes().getNamedItem("y").getNodeValue());
                  int parth = Integer.parseInt(partContents.item(1).getAttributes().getNamedItem("h").getNodeValue());
                  int partw = Integer.parseInt(partContents.item(1).getAttributes().getNamedItem("w").getNodeValue());
                  SetPart.UpdateCord(partx, party, parth, partw);
                  String quote = partContents.item(3).getTextContent();
                  SetPart.UpdateLine(quote);
                  SetPart.UpdateOnCard(false);
                  Set.AddPart(SetPart); 
               }
            }
         }
       //update setlist
      SetList[t] = Set;
      }
      //get the trailer set
      set setTrailer = new set();
      setTrailer.UpdateName("trailer");
      Node trailer_n = trailer.item(0);
      NodeList trailer_items = trailer_n.getChildNodes();
      Node neighborLister = trailer_items.item(1);
      NodeList Neighbors_t = neighborLister.getChildNodes();
      for(int u = 1; u < Neighbors_t.getLength(); u = u+2){
            Node t_neighbor = Neighbors_t.item(u);
            String t_neighborName = t_neighbor.getAttributes().getNamedItem("name").getNodeValue();
            setTrailer.AddNeighbor(t_neighborName);
      }
      Node area_t = trailer_items.item(3);
      int trailerx = Integer.parseInt(area_t.getAttributes().getNamedItem("x").getNodeValue());               
      int trailery = Integer.parseInt(area_t.getAttributes().getNamedItem("y").getNodeValue());
      int trailerh = Integer.parseInt(area_t.getAttributes().getNamedItem("h").getNodeValue());
      int trailerw = Integer.parseInt(area_t.getAttributes().getNamedItem("w").getNodeValue());
      setTrailer.UpdateArea(trailerx, trailery, trailerh, trailerw);
      //add trailer to set list
      SetList[10] = setTrailer;
      //get the office set
      set setOffice = new set();
      setOffice.UpdateName("office");
      Node Officebuilder = Office.item(0);
      NodeList OfficebuilderL = Officebuilder.getChildNodes();
      for(int t = 0; t < OfficebuilderL.getLength(); t++){
         Node OfficeData = OfficebuilderL.item(t);
         if("neighbors".equals(OfficeData.getNodeName())){
            NodeList O_neighbors = OfficeData.getChildNodes();
            for(int u = 1; u < O_neighbors.getLength(); u = u+2){
               Node O_neighbor = O_neighbors.item(u);
               String O_neighborName = O_neighbor.getAttributes().getNamedItem("name").getNodeValue();
               setOffice.AddNeighbor(O_neighborName);
            }
         }
         else if("area".equals(OfficeData.getNodeName())){
            int officex = Integer.parseInt(OfficeData.getAttributes().getNamedItem("x").getNodeValue());               
            int officey = Integer.parseInt(OfficeData.getAttributes().getNamedItem("y").getNodeValue());
            int officeh = Integer.parseInt(OfficeData.getAttributes().getNamedItem("h").getNodeValue());
            int officew = Integer.parseInt(OfficeData.getAttributes().getNamedItem("w").getNodeValue());  
            setOffice.UpdateArea(officex, officey, officeh, officew);
         }
      }
  //add office to set list
  SetList[11] = setOffice;
  }
}

class Scenes{
private DocHandler cardsDoc = new DocHandler();
private Document doc;
private NodeList deck;
private String sceneName;
private String imageNum;
private String SceneDesc;
private int budget;
private int sceneNum;
private part[] partList = new part[3];
private int partIndex = 0;
public boolean flipped = false;
//methods to return vairables of scenes
   public int GiveBudget(){
      return budget;
   }
   public String GiveName(){
      return sceneName;
   }
   public String GiveImage(){
      return imageNum;
   }
   //get card data from xml file
   public void getCards(int cardNum){
      try{
         doc = cardsDoc.getDoc("cards.xml");
         readCards(doc, cardNum);
         }
      catch(ParserConfigurationException ex){
         System.out.println("XML Parse Failure");
         ex.printStackTrace();
         }
   }
   public void readCards(Document doc, int cardNum){
   //store data into different scene objects
      Element root = doc.getDocumentElement();
      deck = root.getElementsByTagName("card");
         Node card = deck.item(cardNum);
         //get name of the card
         sceneName = card.getAttributes().getNamedItem("name").getNodeValue();
         //get the image name/ number
         imageNum = card.getAttributes().getNamedItem("img").getNodeValue();
         //get the budget for the card
         budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
         NodeList children = card.getChildNodes();
         for(int f = 1; f < children.getLength();f = f+2){
            Node grandchild = children.item(f);
            if("scene".equals(grandchild.getNodeName())){
               sceneNum = Integer.parseInt(grandchild.getAttributes().getNamedItem("number").getNodeValue());
               SceneDesc = grandchild.getTextContent();
            }
            else if("part".equals(grandchild.getNodeName())){
               part addPart = new part();
               //find part level and update part class
               int level = Integer.parseInt(grandchild.getAttributes().getNamedItem("level").getNodeValue());
               addPart.UpdateLevel(level);
               //find part name and update part class
               String partName = grandchild.getAttributes().getNamedItem("name").getNodeValue();
               addPart.UpdateName(partName);
               //get child nodes of part
               NodeList parts = grandchild.getChildNodes();
               for(int k =0; k < parts.getLength(); k++){
                  Node partChild = parts.item(k);
                  //find the cordinates of the part and update part class
                  if("area".equals(partChild.getNodeName())){
                     int x = Integer.parseInt(partChild.getAttributes().getNamedItem("x").getNodeValue());
                     int y = Integer.parseInt(partChild.getAttributes().getNamedItem("y").getNodeValue());
                     int h = Integer.parseInt(partChild.getAttributes().getNamedItem("h").getNodeValue());
                     int w = Integer.parseInt(partChild.getAttributes().getNamedItem("w").getNodeValue());
                     addPart.UpdateCord(x,y,h,w);
                  }
                  //find the line the part speaks and update part class
                  else if("line".equals(partChild.getNodeName())){
                     String quote = partChild.getTextContent();
                     addPart.UpdateLine(quote);
                  }
                }
             addPart.UpdateOnCard(true);
             partList[partIndex] = addPart;
             partIndex ++;
             }
          }
   }
   public part[] GiveParts(){
      return partList;
   }
}
//parses the doc to return the data
class DocHandler{
   public static Document getDoc(String filename) throws ParserConfigurationException{
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db= dbf.newDocumentBuilder();
      Document doc = null;
      try{
         doc = db.parse(filename);
      }
      catch(Exception ex){
         System.out.println("XML Parse Failure");
         ex.printStackTrace();
      }
      return doc;
   }
}
//class for the specific parts on set and scenes
class part{
private String partName;
private int partLevel;
private int Xcord;
private int Ycord;
private int Height;
private int Width;
private String partLine;
private boolean partTaken = false;
private boolean OnCard;
//update data
public void UpdateOnCard(boolean card){
   OnCard = card;
}
public void UpdateName(String Name){
   partName = Name;
   return;
   }
public void UpdateLevel(int part){
   partLevel = part;
   return;
   }
public void UpdateLine(String Line){
   partLine = Line;
   return;
   }
public void UpdateCord(int x, int y, int h, int w){
   Xcord = x;
   Ycord = y;
   Height = h;
   Width = w;
   return;
   }
public void UpdateTaken(){
   if(partTaken == true){
      partTaken = false;
      }
   else{
      partTaken = true;
      }
   }
//returning data
public String ReturnName(){
   return partName;   
   }
public int ReturnLevel(){
   return partLevel;
   }
public String ReturnLine(){
   return partLine;
   }
public int[] ReturnCord(){
   int[] cord = new int[4];
   cord[0] = Xcord;
   cord[1] = Ycord;
   cord[2] = Height;
   cord[3] = Width;
   return cord;
   }
public boolean ReturnTaken(){
   return partTaken;   
   }
public boolean ReturnOnCard(){
   return OnCard;
   }
}
//data for single sets
class set{
private String setName;
private String[] neighbors = new String[4];
private int neighborIndex;
private int[] setArea = new int[4];
private part[] partList = new part[4];
private int partIndex;
private int[][] shots = new int[3][4];
private int shotIndex;
private Scenes SceneCard = new Scenes();
private int shotsleft = -1;
private JLabel Card;
//update data
public void UpdateName(String Name){
   setName = Name;
   }
public void UpdateImage(JLabel card){
   Card = card;
}
public void AddNeighbor(String neighbor){
   neighbors[neighborIndex] = neighbor;
   neighborIndex ++; 
   }
public void UpdateArea(int x, int y, int h, int w){
   setArea[0] = x;
   setArea[1] = y;
   setArea[2] = h;
   setArea[3] = w;
   }
public void AddPart(part NewPart){
   partList[partIndex] = NewPart;
   partIndex ++;
   }
public void ShotsArea(int x, int y, int h, int w){
   shots[shotIndex][0] = x;
   shots[shotIndex][1] = y;
   shots[shotIndex][2] = h;
   shots[shotIndex][3] = w;
   shotIndex ++;
   if(shotsleft == -1){
      shotsleft = 1;
   }
   else{
      shotsleft ++;
   }
   }
public void UpdateCard(Scenes card){
   SceneCard = card;
}
public void ShotDone(){
   shotsleft --;
}
//return data
public String returnName(){
   return setName;
   }
public int returnShots(){
   return shotsleft;   
   }
public String[] returnNeighbors(){
   return neighbors;
   }
public int[] returnArea(){
   return setArea;
   }
public part[] returnParts(){
   return partList;
   }
public Scenes returnScene(){
   return SceneCard;
   }
public int[] returnShotArea(){
   return shots[shotIndex-1];
   }
public JLabel returnCard(){
   return Card;   
   }
}
class GUI extends JFrame{
JButton move = new JButton("Move");
JButton claimRole = new JButton("Claim Role");
JButton Act = new JButton("Act");
JButton Reherse = new JButton("Reherse");      
JButton upgrade = new JButton("Upgrade");
JButton endTurn = new JButton("End Turn");
JLabel Board;
JLabel[] Card = new JLabel[10];
JLabel[] pIcons = new JLabel[8];
JLabel Menu;
JLayeredPane Format;
public void build(){
   //creating the frame and board
   setTitle("DEADWOOD");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   Format = getLayeredPane();
   ImageIcon iconB = new ImageIcon("board.jpg");
   Board = new JLabel();
   Board.setIcon(iconB);
   Board.setBounds(0,0,iconB.getIconWidth(),iconB.getIconHeight());
   setSize(iconB.getIconWidth(),iconB.getIconHeight()+100);
   Format.add(Board,new Integer(0));
   //adding the menu label and buttons
   Menu = new JLabel("MENU");
   Menu.setBounds(0,iconB.getIconHeight()+20,100,20);
   Format.add(Menu, new Integer(2));
   move.setActionCommand("move");
   move.setBounds(20,iconB.getIconHeight()+40,110,20);
   claimRole.setActionCommand("claimRole");
   claimRole.setBounds(220,iconB.getIconHeight()+40,110,20);
   Act.setActionCommand("Act");
   Act.setBounds(420,iconB.getIconHeight()+40,110,20);
   Reherse.setActionCommand("Reherse");
   Reherse.setBounds(620,iconB.getIconHeight()+40,110,20);
   upgrade.setActionCommand("upgrade");
   upgrade.setBounds(820,iconB.getIconHeight()+40,110,20);
   endTurn.setActionCommand("endturn");
   endTurn.setBounds(1020,iconB.getIconHeight()+40,110,20);
   move.addActionListener(new Actions());
   claimRole.addActionListener(new Actions());
   Act.addActionListener(new Actions());
   Reherse.addActionListener(new Actions());
   upgrade.addActionListener(new Actions());
   endTurn.addActionListener(new Actions());
   Format.add(move, new Integer(2));
   Format.add(claimRole, new Integer(2));
   Format.add(Act, new Integer(2));
   Format.add(Reherse, new Integer(2));
   Format.add(upgrade, new Integer(2));
   Format.add(endTurn, new Integer(2));
   ImageIcon playerIcon;
   for (int u = 0; u < 8; u++){ 
      pIcons[u] = new JLabel();
      if(u == 0){
         playerIcon = new ImageIcon("b1.png");
      }
      else if(u == 1){
         playerIcon = new ImageIcon("c1.png");
      }
      else if(u == 2){
         playerIcon = new ImageIcon("g1.png");
      }
      else if(u == 3){
         playerIcon = new ImageIcon("o1.png");
      }
      else if(u == 4){
         playerIcon = new ImageIcon("r1.png");
      }
      else if(u == 5){
         playerIcon = new ImageIcon("p1.png");
      }
      else if(u == 6){
         playerIcon = new ImageIcon("v1.png");
      }
      else{
         playerIcon = new ImageIcon("w1.png");
      }
      pIcons[u].setIcon(playerIcon);
      pIcons[u].setBounds(991,275 + (u*10),playerIcon.getIconWidth(),playerIcon.getIconHeight());
      pIcons[u].setVisible(false);
      Format.add(pIcons[u], new Integer(3));
   }
  }
  public void CardBuild(set[] location){
   //adding the first card
   for(int u = 0; u < 10; u++){
      Card[u] = new JLabel();
      int[] cords = location[u].returnArea();
      ImageIcon cIcon =  new ImageIcon("CardBack.jpg");
      Card[u].setIcon(cIcon); 
      Card[u].setBounds(cords[0],cords[1],cIcon.getIconWidth()+2,cIcon.getIconHeight());
      Card[u].setOpaque(true);
      // Add the card to the lower layer and set
      location[u].UpdateImage(Card[u]);
      Format.add(Card[u], new Integer(1));
   } 
  }
  public void MovePlayerIcon(Player player, set NewLoc){
   int[] Icord = NewLoc.returnArea();
   JLabel mover = player.GiveIcon();
   mover.setBounds(Icord[0]+((player.PlayerNum-1) * 10), Icord[1]+115, mover.getIcon().getIconWidth(), mover.getIcon().getIconHeight());
  }
  public void PlayerRole(Player player, part Role){
   int[] partCord = Role.ReturnCord();
   JLabel claim = player.GiveIcon();
   if(Role.ReturnOnCard()){
      int[] setCord = player.GivePosition().returnArea();
      claim.setBounds(setCord[0] + partCord[0],setCord[1] + partCord[1] , claim.getIcon().getIconWidth(), claim.getIcon().getIconHeight());
   }
   else{
      claim.setBounds(partCord[0], partCord[1],claim.getIcon().getIconWidth(), claim.getIcon().getIconHeight());
   }
  }
}
class Actions implements ActionListener{
   public void actionPerformed(ActionEvent e){
      if("move".equals(e.getActionCommand())){
         Game.Move(Game.PlayerList[Game.tracker]);
         System.out.print("moving...\n");
      }
      else if("claimRole".equals(e.getActionCommand())){
         Game.ClaimingRole(Game.PlayerList[Game.tracker]);
         System.out.print("claiming role...\n");
      }
      else if("Act".equals(e.getActionCommand())){
         boolean SceneDone = Game.Act(Game.PlayerList[Game.tracker], Game.PlayerList[Game.tracker].Act(Game.PlayerList[Game.tracker].GiveTokens()));
         boolean Bonus = false;
         //boolean SceneDone = Act(PlayerList[tracker], PlayerList[tracker].Act(PlayerList[tracker].GiveTokens()));
         if(SceneDone){
            set CheckerSet = Game.PlayerList[Game.tracker].GivePosition();
            Scenes CheckerCard = CheckerSet.returnScene();
            part[] CheckerScene = CheckerCard.GiveParts();
            for(int g = 0; g < 3; g++){
               if(CheckerScene[g].ReturnTaken()){
                  Bonus = true;
               }
            }
            if(Bonus){
               Game.BonusPayout(Game.PlayerList[Game.tracker], Game.PlayerList);
            }
            for(int c = 0; c < Game.TotalPlayers; c++){
               if(Game.PlayerList[c].GivePosition() == Game.PlayerList[Game.tracker].GivePosition()){
                  Game.PlayerList[c].GivePart().UpdateTaken();
                  Game.PlayerList[c].ResetPart();
               }
            } 
         }
         Game.PlayerList[Game.tracker].resetMove();
         System.out.print("Acting...\n");
      }
      else if("Rehearse".equals(e.getActionCommand())){
         Game.PlayerList[Game.tracker].Rehearse();
         System.out.print("Rehearsing...\n");
      }
      else if("upgrade".equals(e.getActionCommand())){
         System.out.print("upgrading...\n");
      }
      else if("endturn".equals(e.getActionCommand())){
         System.out.print("ending turn...\n");
      }
     }
}