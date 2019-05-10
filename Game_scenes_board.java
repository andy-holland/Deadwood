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
   private static void Rehearse(){
   ///do this
   }
   private static void Upgrade(int money, int credits, int rank){
   //do this
   }
   private static void BonusRoll(){
   //take in from dice
   }
   private static void ClaimRole(){
   }
   private static void EndTurn(){
   }
   private static void AddMoney(int payout){
   //do this
   }
   private static void AddCredits(int payout){
   //do this
   }
   private static void AddToken(){
   //do this
   }
}
class Dice{
int NumOfDice;
static int SumOfDice;
   public static void RollDice(int NumOfDice){
	   for(int i = 0; i < NumOfDice; i++){
		   int roll = (int)(Math.random() * 6 + 1);
		   SumOfDice += roll;
	   }
      System.out.println(SumOfDice);
   }
   private static int GiveSum(int SumOfDice){
	   return(SumOfDice);
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
      for(int t =0; t < board.getLength(); t++){
         Node set = board.item(t);
         String setName = set.getAttributes().getNamedItem("name").getNodeValue();
         NodeList inside = set.getChildNodes();
         for(int q = 0; q < inside.getLength(); q++){
            Node setData = inside.item(q);
            if("neighbors".equals(setData.getNodeName())){
               NodeList neighbors = setData.getChildNodes();
               for(int u = 0; u < neighbors.getLength(); u ++){
                  Node neighbor = neighbors.item(u);
                  String neighborName = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                  neighborList[u] = neighborName;
               }
            }
            else if("area".equals(setData.getNodeName())){
               int x = Integer.parseInt(setData.getAttributes().getNamedItem("x").getNodeValue());
               int y = Integer.parseInt(setData.getAttributes().getNamedItem("y").getNodeValue());
               int h = Integer.parseInt(setData.getAttributes().getNamedItem("h").getNodeValue());
               int w = Integer.parseInt(setData.getAttributes().getNamedItem("w").getNodeValue());
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
               }
            }
            else if("parts".equals(setData.getNodeName())){
               NodeList parts = setData.getChildNodes();
               for(int u = 0; u < parts.getLength(); u++){
                  Node part = parts.item(u);
                  String partName = part.getAttributes().getNamedItem("name").getNodeValue();
                  int partLevel = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());
                  NodeList partContents = part.getChildNodes();
                  int partx = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("x").getNodeValue());               
                  int party = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("y").getNodeValue());
                  int parth = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("h").getNodeValue());
                  int partw = Integer.parseInt(partContents.item(0).getAttributes().getNamedItem("w").getNodeValue());
                  String quote = partContents.item(1).getTextContent(); 
               }
            }
         }
      }
      for(int t = 0; t < trailer.getLength();t++){
         Node trailer_n = trailer.item(t);
         NodeList trailer_neighbors = trailer_n.getChildNodes();
         for(int u = 0; u < trailer_neighbors.getLength(); u ++){
               Node t_neighbor = trailer_neighbors.item(u);
               String t_neighborName = t_neighbor.getAttributes().getNamedItem("name").getNodeValue();
               TrailerNeighbors[u] = t_neighborName;
         }
      }
      for(int t = 0; t < Office.getLength();t++){
      Node OfficeData = Office.item(t);
         if("neighbors".equals(OfficeData.getNodeName())){
            NodeList O_neighbors = OfficeData.getChildNodes();
            for(int u = 0; u < O_neighbors.getLength(); u ++){
               Node O_neighbor = O_neighbors.item(u);
               String O_neighborName = O_neighbor.getAttributes().getNamedItem("name").getNodeValue();
               OfficeNeighbors[u] = O_neighborName;
            }
         }
      }
   }
}

class Scenes{
private DocHandler cardsDoc = new DocHandler();
private Document doc;
private NodeList deck;
   public void getCards(){
      try{
         doc = cardsDoc.getDoc("cards.xml");
         }
      catch(ParserConfigurationException ex){
         System.out.println("XML Parse Failure");
         ex.printStackTrace();
         }
   }
   public void readCards(Document doc){
      Element root = doc.getDocumentElement();
      deck = root.getElementsByTagName("card");
      for(int j = 0; j < deck.getLength(); j++){
         Node card = deck.item(j);
         String sceneName = card.getAttributes().getNamedItem("name").getNodeValue();
         String imageNum = card.getAttributes().getNamedItem("img").getNodeValue();
         String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
         NodeList children = card.getChildNodes();
         for(int f = 0; f < children.getLength();f++){
            Node grandchild = children.item(f);
            if("scene".equals(grandchild.getNodeName())){
               int sceneNum = Integer.parseInt(grandchild.getAttributes().getNamedItem("number").getNodeValue());
               String sceneDesc = grandchild.getTextContent();
            }
            else if("part".equals(grandchild.getNodeName())){
               int level = Integer.parseInt(grandchild.getAttributes().getNamedItem("level").getNodeValue());
               String partName = grandchild.getAttributes().getNamedItem("name").getNodeValue();
               NodeList parts = grandchild.getChildNodes();
               for(int k =0; k < parts.getLength(); k++){
                  Node partChild = parts.item(k);
                  if("area".equals(partChild.getNodeName())){
                     int x = Integer.parseInt(partChild.getAttributes().getNamedItem("x").getNodeValue());
                     int y = Integer.parseInt(partChild.getAttributes().getNamedItem("y").getNodeValue());
                     int h = Integer.parseInt(partChild.getAttributes().getNamedItem("h").getNodeValue());
                     int w = Integer.parseInt(partChild.getAttributes().getNamedItem("w").getNodeValue());
                  }
                  else if("line".equals(partChild.getNodeName())){
                     String quote = partChild.getTextContent();
                  }
                }
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