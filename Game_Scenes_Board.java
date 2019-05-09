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