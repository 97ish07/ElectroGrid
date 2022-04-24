package payment;
// REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*;

import model.Card;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payment")
 
public class CardtService 
{ 
Card itemObj = new Card(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 { 
	return itemObj.readCardData(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertCardData(@FormParam("cardHolderName") String cardHolderName, 
 @FormParam("cardNo") String cardNo, 
 @FormParam("Month") String Month, 
 @FormParam("Year") String Year, 
@FormParam("ccvNo") String ccvNo)
{ 
 String output = itemObj.insertCardData(cardHolderName, cardNo, Month, Year,ccvNo); 
return output; 
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCard(String itemData) 
{ 
//Convert the input string to a JSON object 
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
//Read the values from the JSON object
 String itemID = itemObject.get("itemID").getAsString(); 
 String cardHolderName = itemObject.get("cardHolderName").getAsString(); 
 String cardNo = itemObject.get("cardNo").getAsString(); 
 String Month = itemObject.get("Month").getAsString(); 
 String Year = itemObject.get("Year").getAsString();
 String ccvNo = itemObject.get("ccvNo").getAsString(); 
 String output = itemObj.updateCard(itemID, cardHolderName, cardNo, Month, Year,ccvNo); 
return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCard(String itemData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
 
//Read the value from 
 String itemID = doc.select("itemID").text(); 
 String output = itemObj.deleteCard(itemID); 
return output; 
}





}
