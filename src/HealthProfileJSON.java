import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import dao.PeopleStore;
import model.HealthProfile;
import model.Person;

public class HealthProfileJSON {

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
	
	public static void main(String[] args) {
		
		try{
			initializeDB();
			
			// Jackson Object Mapper
			ObjectMapper mapper = new ObjectMapper();
			
			// Adding the Jackson Module to process JAXB annotations
			JaxbAnnotationModule module = new JaxbAnnotationModule();
			
			//Mapper configuration
			mapper.registerModule(module);
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
			
			String result = mapper.writeValueAsString(people);
			System.out.println(result); 						//marshalling into the System default output
			mapper.writeValue(new File("people.json"), people); //marshalling into Json file
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
