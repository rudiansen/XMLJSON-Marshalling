import java.io.FileReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import dao.PeopleStore;
import model.Person;

public class HealthProfileWriter {

	public static void main(String[] args) {

		try{
			
			JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
    		System.out.println();
    		System.out.println("Output from XML file: ");
    		Unmarshaller um = jc.createUnmarshaller();
    		PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people_new.xml"));
    		List<Person> list = people.getData();
            for (Person person : list) {
            	System.out.println("\n===================================================================\n\n"
			 			+ "	ID		: " + person.gePersontId() + "\n"
	 					+ "	Firstname	: " + person.getFirstname() + "\n"
			 			+ "	Lastname	: " + person.getLastname() + "\n"
			 			+ "	Birthdate	: " + person.getBirthdate() + "\n"
			 			+ "	Weight		: " + person.gethProfile().getWeight() + " Kg\n"
			 			+ "	Height		: " +  person.gethProfile().getHeight() + " m\n"
			 			+ "	BMI		: " + person.gethProfile().getBMI());                    	
            }                                                 		            		    	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
