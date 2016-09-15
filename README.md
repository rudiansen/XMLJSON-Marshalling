# XML and JSON Marshalling

This project shows an example of converting XML contents into Java objects (unmarshalling) and vice versa (marshalling). In addition, it will also demonstrate how to marshall Java objects into JSON format. 

## How does it work?

Basically the program will load an XML file (i.e. person.xml) into memory using DOM interface and query the data using XPath to print it in the terminal. After successfully loaded the XML file and converted it into Java objects, it will marshall those Java objects into a new XML file (i.e. people_new.xml). It will also marshall those Java obejcts into JSON format by creating a new JSON file (i.e. person.json).

## How to execute the program?

To execute the program, you can clone this repository onto your local machine by following below steps:

	1. Type on your terminal git clone https://github.com/rudiansen/XMLJSON-Marshalling.git
	2. cd XMLJSON-Marshalling
	3. ant execute.evaluation

If you want to run a specific method, you can type `ant target name` contained in build.xml file. For example, to run `getPeopleByID(personID)` method, you can type the following command: 
	`ant execute.getPeopleByID`

PS: To be able to execute this command, you are expected to have ANT set on your machine.

  