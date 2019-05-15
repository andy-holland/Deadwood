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
private DocHandler boardDoc = new DocHandler();
private Document docB;
   public void getBoard(){
      try{
         docB = boardDoc.getDoc("board.xml");
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
      String neighborList[] = new String[4];
      String TrailerNeighbors[] = new String[4];
      String OfficeNeighbors[] = new String[4];
      set[] SetList = new set[12];
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
               for(int u = 0; u < neighbors.getLength(); u ++){
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
               for(int u = 0; u < takeList.getLength(); u++){
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
               for(int u = 0; u < parts.getLength(); u++){
                  Node part = parts.item(u);
                  String partName = part.getAttributes().getNamedItem("name").getNodeValue();
                  part SetPart = new part();
                  SetPart.UpdateName(partName);
                  int partLevel = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
                  SetPart.UpdateLevel(partLevel);
                  NodeList partContents = part.getChildNodes();
                  int partx = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("x").getNodeValue());               
                  int party = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("y").getNodeValue());
                  int parth = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("h").getNodeValue());
                  int partw = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("w").getNodeValue());
                  SetPart.UpdateCord(partx, party, parth, partw);
                  String quote = partContents.item(1).getTextContent();
                  SetPart.UpdateLine(quote);
                  Set.AddPart(SetPart); 
               }
            }
         SetList[q] = Set;
         }
      }
      set setTrailer = new set();
      setTrailer.UpdateName("trailer");
      Node trailer_n = trailer.item(0);
      NodeList trailer_items = trailer_n.getChildNodes();
      Node neighborLister = trailer_items.item(0);
      NodeList Neighbors_t = neighborLister.getChildNodes();
      for(int u = 0; u < Neighbors_t.getLength(); u ++){
            Node t_neighbor = Neighbors_t.item(u);
            String t_neighborName = t_neighbor.getAttributes().getNamedItem("name").getNodeValue();
            TrailerNeighbors[u] = t_neighborName;
            setTrailer.AddNeighbor(t_neighborName);
      }
      Node area_t = trailer_items.item(1);
      int trailerx = Integer.parseInt(area_t.getAttributes().getNamedItem("x").getNodeValue());               
      int trailery = Integer.parseInt(area_t.getAttributes().getNamedItem("y").getNodeValue());
      int trailerh = Integer.parseInt(area_t.getAttributes().getNamedItem("h").getNodeValue());
      int trailerw = Integer.parseInt(area_t.getAttributes().getNamedItem("w").getNodeValue());
      setTrailer.UpdateArea(trailerx, trailery, trailerh, trailerw);
      set setOffice = new set();
      Node OfficeN = Office.item(0);
      NodeList InsideOffice = OfficeN.getChildNodes();
      for(int t = 0; t < InsideOffice.getLength(); t++){
         Node OfficeData = Office.item(t);
         if("neighbors".equals(OfficeData.getNodeName())){
            NodeList O_neighbors = OfficeData.getChildNodes();
            for(int u = 0; u < O_neighbors.getLength(); u ++){
               Node O_neighbor = O_neighbors.item(u);
               String O_neighborName = O_neighbor.getAttributes().getNamedItem("name").getNodeValue();
               OfficeNeighbors[u] = O_neighborName;
               setOffice.UpdateName(O_neighborName);
            }
         }
         else if("area".equals(OfficeData.getNodeName())){
            int officex = Integer.parseInt(area_t.getAttributes().getNamedItem("x").getNodeValue());               
            int officey = Integer.parseInt(area_t.getAttributes().getNamedItem("y").getNodeValue());
            int officeh = Integer.parseInt(area_t.getAttributes().getNamedItem("h").getNodeValue());
            int officew = Integer.parseInt(area_t.getAttributes().getNamedItem("w").getNodeValue());  
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
  }
}

class Scenes{
private DocHandler cardsDoc = new DocHandler();
private Document doc;
private NodeList deck;
private String sceneName;
private String imageNum;
private String budget;
private int sceneNum;
private part[] partList = new part[3];
private int partIndex = 0;
   public void getCards(){
      try{
         doc = cardsDoc.getDoc("cards.xml");
         }
      catch(ParserConfigurationException ex){
         System.out.println("XML Parse Failure");
         ex.printStackTrace();
         }
   }
   public void readCards(Document doc, int cardNum){
      Element root = doc.getDocumentElement();
      deck = root.getElementsByTagName("card");
      for(int j = 0; j < deck.getLength(); j++){
         Node card = deck.item(j);
         sceneName = card.getAttributes().getNamedItem("name").getNodeValue();
         imageNum = card.getAttributes().getNamedItem("img").getNodeValue();
         budget = card.getAttributes().getNamedItem("budget").getNodeValue();
         NodeList children = card.getChildNodes();
         for(int f = 0; f < children.getLength();f++){
            Node grandchild = children.item(f);
            if("scene".equals(grandchild.getNodeName())){
               sceneNum = Integer.parseInt(grandchild.getAttributes().getNamedItem("number").getNodeValue());
               String sceneDesc = grandchild.getTextContent();
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
             partList[partIndex] = addPart;
             partIndex ++;
             }
          }
       }
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
public int[] returnShotArea(){
   return shots[shotIndex-1];
   }
}