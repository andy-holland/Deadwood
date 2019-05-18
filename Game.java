import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
class Game{
static Board DefaultBoard = new Board();
static Scenes[] SceneDeck = new Scenes[40];
static int[] UsedCards = new int[40];
static int UsedCardIndex = 0;
static set[] SetList = new set[12];
static int SceneCardTotal;
static int DaysLeft = 4;
static int TotalPlayers;
static boolean EndTurn = false;
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
   if(TotalPlayers < 4){
      DaysLeft = 3;
   }
   System.out.println("Starting the game with "+TotalPlayers+" players\n");
   //System.out.println("Press enter to start the game\n");
   //read.nextLine();
   //1. Set up Board, Rooms, Players
   //players
   SetupBoard();
   SetupCards();
   DistrCards();
   Player[] PlayerList = new Player[TotalPlayers];
   for(int p = 0; p < TotalPlayers; p++){
      Player Initial = Player.Builder(TotalPlayers);
      if(Initial != null){
         PlayerList[p] = Initial;
      }
      int translate = p+1;
      System.out.println("What is the name of Player "+translate+"?\n");
      String name = read.nextLine();
      PlayerList[p].UpdateName(name);
   }
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
      DefaultBoard.getBoard();
      set[] SetList = new set[12];
      SetList = DefaultBoard.returnSetlist();
   }
   
   private static void SetupCards(){
      for(int t = 0; t < 40; t++){
         Scenes Card = new Scenes();
         Card.getCards(t);
         SceneDeck[t] = Card;
         }
   }
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
   private static void ClaimingRole(Player player){
      part Role = player.ClaimRole();
      while(Role != null){
         if(CheckRank(player, Role)){
            Role.UpdateTaken();
            player.UpdateRole(Role);
            break;
         }
         else{
            System.out.println("you are not high enough rank for that role.");
            ClaimingRole(player);
         }
      }
   }
   private static boolean CheckRank(Player player, part part){
      boolean allowed = false;
      if(player.GiveRank() >= part.ReturnLevel()){
         allowed = true;
      }
      return allowed;
   }
   private static boolean CheckCastingOffice(Player player){
      boolean office = false;
      if(player.GivePosition() == SetList[11]){
         office = true;
      }
   return office;
   }
   private static void Payout(Player player){
      boolean card = player.GiveOnCard();
      if(card){
         player.AddCredits(2);
      }
      else{
         player.AddCredits(1);
         player.AddMoney(1);
      }
      
   }
   private static void BonusPayout(Player player, Player[] PlayerList){
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
   private static void ResetDay(Player[] PlayerList){
      SetList = DefaultBoard.returnSetlist();
      DistrCards();
      for(int r = 0; r < TotalPlayers; r++){ 
         PlayerList[r].UpdateLoc(SetList[10]);
      }
      DaysLeft --;
   }
   private static void EndGame(){
   
   }
}

class Player{
private static int instance;
private int rank = 1;
private static int tokens = 0;
private String role;
//id of player
String PlayerName;
int PlayerNum;
private set currentposition = new set();
private part playerpart = new part();
private int money = 0;
private int credits = 0;
private boolean hasmoved = false;
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
   public void UpdateRole(part role){
      playerpart = role;
   }
   public void UpdateLoc(set setname){
      currentposition = setname;
   }
   public void UpdateName(String name){
      PlayerName = name;
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
   public boolean GiveOnCard(){
      return playerpart.ReturnOnCard();
   }
   //method to run current player's turn
   public String PlayerTurn(){
        //open scanner 
        Scanner read = new Scanner(System.in);
        String input;
        System.out.println("Player"+PlayerNum+"'s turn. Your options are:\n");

        //list available options
        if(canMove(hasmoved) == true){
        	System.out.println(" move ");
        }

        if(canAct() == true){
        	System.out.println(" act (this will end your turn)");
        }

        if(canUpgrade() == true){
        	System.out.println(" upgrade ");
        }

        if(canRehearse() == true){
        	System.out.println(" rehearse (this will end your turn)");
        }

	if(canClaimRole() == true){
		System.out.println(" claim role (this will end your turn)");
	}

        System.out.println(" end turn ");
        //take input
        input = read.nextLine();
        if(read.equals("move") && canMove(hasmoved)){
		input = "move";
		hasmoved = true;
	}
	
	else if(read.equals("act") && canAct()){
		input = "act";
	}

	else if(read.equals("upgrade") && canUpgrade()){
		input = "upgrade";
	}

	else if(read.equals("rehearse") && canRehearse()){
		input = "rehearse";
	}

	else if(read.equals("end turn")){
		input = "end turn";
	}

	else if(read.equals("claim role") && canClaimRole()){
		input = "claim role";
	}

	else{
		System.out.println("invalid input");
		input = null;
	}

        //close scanner
        read.close();
        return input;
   }

   boolean canClaimRole(){
	boolean canclaimrole = false;
	if(playerpart.ReturnName() != null){
		canclaimrole = false;
	}

        part[] options = currentposition.returnParts();
        for(int i = 0; i < 4; i++){
        	if(options[i].ReturnTaken() == false){
			canclaimrole = true;
         	}
        }
	
   	return canclaimrole;
   }

   boolean canMove(boolean hasmoved){
	boolean canmove = true;
	if(playerpart.ReturnName() != null){
		canmove = false;
	}
	else if(hasmoved = true){
		canmove = false;
	}	
	return canmove;
   }

   private boolean canAct(){ 
      boolean canact = true;
      if(playerpart.ReturnName() == null){
      	canact = false;
      }
      return canact;
   }

   private boolean canUpgrade(){
      //currentposition
      boolean canupgrade = false;
      if(currentposition.returnName().equals("Office")){
        canupgrade = true;
      } 
      return canupgrade;
   }

   private boolean canRehearse(){
      boolean canrehearse = true;
      if(playerpart.ReturnName() == null){
        canrehearse = false;
      }
      return canrehearse;
   }

   private boolean canTakeRole(){
      boolean cantakerole = true;
      if(playerpart.ReturnName() != null){
        cantakerole = false;
      }
      return cantakerole;
   }

   private void GetInfo(){
      System.out.println("Player name: "+PlayerName);
      System.out.println("Rank: "+rank);
      System.out.println("money: "+money);
      System.out.println("credits: "+credits);
      System.out.println("set: "+currentposition.returnName());
      if(playerpart.ReturnName() != null){
         System.out.println("scene: "+currentposition.returnScene());
         System.out.println("role: "+playerpart.ReturnName());
      }
   }
   private String Move(int position){
               Scanner read = new Scanner(System.in);
               //if move:
               //check which moves are available
               String currentpos = currentposition.returnName();
               String[] neighbors = currentposition.returnNeighbors();
               System.out.println("You are in: "+currentpos);
               //display available moves with option to go back to (A)
               System.out.println("You can move to:");
               for(int i = 0; i < 4; i++){
                  if(neighbors[i] != null){
                     System.out.println(neighbors[i]);
                  }
               }
               //take input
               String move = read.nextLine();
               read.close();
               return move;
   }
   public int Act(int tokens){
      Dice dice = new Dice();
      return dice.RollDice() + tokens;
   }
   public void Rehearse(){
      tokens++;
   }
   public static void Upgrade(int money, int credits, int rank, int desired_rank, int money_or_credits){
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
   public part ClaimRole(){//changes playerpart
      Scanner read = new Scanner(System.in);
      part[] optionsB = currentposition.returnParts();
      part[] optionsS = currentposition.returnScene().GiveParts();
      part[] optionslist = new part[8];
      int optionsIndex = 0;
      System.out.println("These are the roles available:");
      for(int i = 0; i < 4; i++){
         if(optionsB[i].ReturnTaken() == false && optionsB != null){
            optionslist[optionsIndex] = optionsB[i];
            System.out.println(""+optionsIndex+". "+optionslist[optionsIndex].ReturnName()+" level: "+optionslist[optionsIndex].ReturnLevel());
            optionsIndex ++;
         }
      }
      for(int i = 0; i < 3; i++){
         if(optionsS[i].ReturnTaken() == false && optionsS[i] != null){
            optionslist[optionsIndex] = optionsS[i];
            System.out.println(""+optionsIndex+". "+optionslist[optionsIndex].ReturnName()+" level: "+optionslist[optionsIndex].ReturnLevel());
            optionsIndex ++;
         }
      }
      System.out.println(optionsIndex+". Do not claim a role");
      int role = read.nextInt();
      read.close(); 
      return optionslist[role];
   }
   public void AddMoney(int payout){
      money += payout;
   }
   public int GiveMoney(){
      return money;
   }
   public void AddCredits(int payout){
      credits =+ payout;
   }
   public int GiveCredits(){
      return credits;
   }
}

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
   public set[] returnSetlist(){
      return SetList;
   }
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
            if("neighbors".equals(setData.getNodeName())){
               NodeList neighbors = setData.getChildNodes();
               for(int u = 1; u < neighbors.getLength(); u = u+2){
                  Node neighbor = neighbors.item(u);
                  String neighborName = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                  Set.AddNeighbor(neighborName);
               }
            }
            else if("area".equals(setData.getNodeName())){
               int x = Integer.parseInt(setData.getAttributes().getNamedItem("x").getNodeValue());
               int y = Integer.parseInt(setData.getAttributes().getNamedItem("y").getNodeValue());
               int h = Integer.parseInt(setData.getAttributes().getNamedItem("h").getNodeValue());
               int w = Integer.parseInt(setData.getAttributes().getNamedItem("w").getNodeValue());
               Set.UpdateArea(x,y,h,w);
            }
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
      SetList[t] = Set;
      }
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
      SetList[10] = setTrailer;
      set setOffice = new set();
      setOffice.UpdateName("Office");
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
         /*
         else if("upgrades".equals(OfficeData.getNodeName())){
            NodeList upgrades = OfficeData.getChildNodes();
            for(int q = 0; q < upgrades.getLength();q++){
               Node upgrade = upgrades.item(q);
               int upgradeLevel = Integer.parseInt(upgrade.getAttributes().getNamedItem("level").getNodeValue());
               String currency = upgrade.getAttributes().getNamedItem("currency").getNodeValue();
               int price = Integer.parseInt(upgrade.getAttributes().getNamedItem("amt").getNodeValue());
               NodeList upgradeArea = upgrade.getChildNodes();
               int upgradex = Integer.parseInt(upgradeArea.item(0).getAttributes().getNamedItem("x").getNodeValue());               
               int upgradey = Integer.parseInt(upgradeArea.item(0).getAttributes().getNamedItem("y").getNodeValue());
               int upgradeh = Integer.parseInt(upgradeArea.item(0).getAttributes().getNamedItem("h").getNodeValue());
               int upgradew = Integer.parseInt(upgradeArea.item(0).getAttributes().getNamedItem("w").getNodeValue());
            }
         }
         */
      }
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
//get card data from xml file
   public int GiveBudget(){
      return budget;
   }
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
   if(partTaken = true){
      partTaken = false;
   }
   else{
      partTaken = true;
   }
}
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
public void UpdateName(String Name){
   setName = Name;
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
   }
public void UpdateCard(Scenes card){
   SceneCard = card;
}
public String returnName(){
   return setName;
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
}