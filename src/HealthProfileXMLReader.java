import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class HealthProfileXMLReader {
	
	Document doc;
    XPath xpath;
    
    public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("people.xml");

        //creating xpath object
        getXPathObj();
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }

    public NodeList getAllPeople() throws XPathExpressionException{
    	
    	XPathExpression expr = xpath.compile("//person");
    	NodeList personNodeList = (NodeList) expr.evaluate(doc,XPathConstants.NODESET);
    	return personNodeList;
    }
    
    public Node getPeopleByID(String ID) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id='"+ ID +"']");
        Node personNode = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return personNode;
    }
    
    public String getWeightByID(String id) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id='" + id + "']/healthprofile/weight");
        Node weightNode = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return weightNode.getTextContent();
    }
    
    public String getHeightByID(String id) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/people/person[@id='" + id + "']/healthprofile/height");
        Node heightNode = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return heightNode.getTextContent();
    }
    
    public NodeList getPeopleByWeight(String condition, String weight) throws XPathExpressionException {
        
    	XPathExpression expr = xpath.compile("//person[healthprofile/weight "+condition +" '"+weight+"']");
        NodeList personNode = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return personNode;
    }
    
    public void printPersonNode(Node personNode){
    	
    	String personID = "";
    	String firstName = "";
    	String lastName = "";    	
		String birthDate = "";
		String weight = "";
		String height = "";
		String bmi = "";	
		String lastUpdate = "";
		
    	try{    		       		     	   		
	   		 if(personNode.getNodeType() == Node.ELEMENT_NODE){                			
	   			 
	   			 Element personElement = (Element) personNode.getChildNodes();   
	   		                			
	   			 personID = personElement.getAttribute("id");
	   			 firstName = personElement.getElementsByTagName("firstname").item(0).getTextContent();
	   			 lastName = personElement.getElementsByTagName("lastname").item(0).getTextContent();
	   			 birthDate = personElement.getElementsByTagName("birthdate").item(0).getTextContent();
	   			 weight = personElement.getElementsByTagName("weight").item(0).getTextContent();
	   			 height = personElement.getElementsByTagName("height").item(0).getTextContent();
	   			 bmi = personElement.getElementsByTagName("bmi").item(0).getTextContent();  
	   			 lastUpdate = personElement.getElementsByTagName("lastupdate").item(0).getTextContent();	           
	   		 }                		 
	   		 
	   		 System.out.println("\n==================================================================\n\n"
	   				 			+ "	ID		: " + personID + "\n"
	   		 					+ "	Firstname	: " + firstName + "\n"
	   				 			+ "	Lastname	: " + lastName + "\n"
	   				 			+ "	Birthdate	: " + birthDate + "\n"
	   				 			+ "	Weight		: " + weight + " Kg\n"
	   				 			+ "	Height		: " + height + " m\n"
	   				 			+ "	BMI		: " + bmi +"\n"
	   				 			+ "	Last update	: " + lastUpdate);
	   	}    	
    	catch(Exception e){
    		e.printStackTrace();
    	}    	
    }
    
    public static void main(String[] args) {
    	
    	String personID = "";
    	String firstName = "";
    	String lastName = "";    				
    	
    	try{
    		if(args.length > 0){ //Check if there is at least one argument while executing the application
    			
    			HealthProfileXMLReader HPXMLReader = new HealthProfileXMLReader();
            	HPXMLReader.loadXML(); 
            	
            	if(args[0].equalsIgnoreCase("displayHealthProfile")){ //Checking the first argument
            		
            		personID = args[1];            		
            		     
            		Node person = HPXMLReader.getPeopleByID(personID);
            		
            		if(person.getNodeType() == Node.ELEMENT_NODE){
            			
            			Element personElement = (Element) person.getChildNodes();
            			
            			firstName = personElement.getElementsByTagName("firstname").item(0).getTextContent();
           			 	lastName = personElement.getElementsByTagName("lastname").item(0).getTextContent();
           			 
            			System.out.println(firstName + " " + lastName + " with ID = " + personID + " has a weight of " + HPXMLReader.getWeightByID(personID) //Get weight of a person by its ID
            			+ " Kg. and a height of " + HPXMLReader.getHeightByID(personID) + " m"); //Get height of a person by its ID
            		}                	                    
            	}
            	else if(args[0].equalsIgnoreCase("printAllPeople")){ //Checking the first argument           		            		            		            		 
            		 
		        	NodeList personNList = HPXMLReader.getAllPeople(); //Get Node List of person 
		        	 
		        	for (int k=0; k<personNList.getLength(); k++){ //Iterate the Node list and print the data
		    	   		Node personNode = personNList.item(k);         
		    	   		 
		    	   		HPXMLReader.printPersonNode(personNode);
		        	}
                	 
            	}
            	else if(args[0].equalsIgnoreCase("getPeopleByID")){ //Checking the first argument          		
            		
            		personID = args[1];
            		Node personNode = HPXMLReader.getPeopleByID(personID); //Get the Node of a Person with the given ID
            		
            		HPXMLReader.printPersonNode(personNode); //Print the data 
            	}
            	else if(args[0].equalsIgnoreCase("getPeopleByWeight")){ //Checking the first argument           		            		      
            		            		
            		String condition;
            		String number;
            		
            		String params = args[1]; 
            		
            		condition = params.substring(0, 1); //Get the condition of weight from the first character of the parameter
            		
            		//Checking the condition. It only allows either ">", "<" or "="
            		if(condition.equalsIgnoreCase(">") || condition.equalsIgnoreCase("<") || condition.equalsIgnoreCase("=")){
            			
            			number = params.substring(1, params.length()); //Get the number of weight after the condition character from the parameter           	
            		
            			//Get Node List of Person which matches with the given condition
            			NodeList personNList = HPXMLReader.getPeopleByWeight(condition, number);
                		
                		for(int k=0; k<personNList.getLength(); k++){ //Iterate the Node List of Person and print the data
                			Node personNode = personNList.item(k);             		
                			
                			HPXMLReader.printPersonNode(personNode);
                		}
            		}
            	}            	            	            		
    		}    
    		else {
    			System.out.println("Usage: java HealthProfileReader argument[s]\n");
    			System.out.println("Below are the list of possible argument[s]:\n\n"    					
    					+ "=> displayProfileHealth firstName lastName\n"
    					+ "=> printAllPeople\n"
    					+ "=> getPeopleByID personID\n"
    					+ "=> getPeopleByWeight condition weight\n"
    					+ "	e.g. getPeopleByWeight \">\"90\n");
    		}
    	}
    	catch(Exception e){    		    		
    		System.out.println("\n============= Invalid or incomplete command! =============");
    	}    	    	
    }    
}