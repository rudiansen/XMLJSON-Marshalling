import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import dao.PeopleStore;
import model.HealthProfile;
import model.Person;

public class HealthProfileReader {

	public static PeopleStore people = new PeopleStore();
	
	public static void initializeDB() {
		
		//Creating 3 objects of Person
		HealthProfile hpDaniel = new HealthProfile(58.0, 1.68);
		Person daniel = new Person(new Long(1), "Daniel", "Bruzual", "1992-02-23", hpDaniel);
		
		HealthProfile hpNavid = new HealthProfile(65.0, 1.70);
		Person navid = new Person(new Long(2), "Navid", "Samsizadeh", "1989-07-10", hpNavid);
		
		HealthProfile hpCharles = new HealthProfile(82.0, 1.75);
		Person charles = new Person(new Long(3), "Charles", "Ferrari", "1992-10-28", hpCharles);

		people.getData().add(daniel);
		people.getData().add(navid);
		people.getData().add(charles);
	}	
	
	public static void main(String[] args){
		
		try{			
			initializeDB();
			
			JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
    		Marshaller m = jc.createMarshaller();
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    		
    		m.marshal(people, new File("people_new.xml")); //marshalling into XML file
    		m.marshal(people, System.out); //marshalling into the System default output
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
