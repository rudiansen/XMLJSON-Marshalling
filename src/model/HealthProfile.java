package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;

import java.text.DecimalFormat;

@XmlRootElement(name="healthprofile")
@XmlType(propOrder = {"weight", "height", "BMI" })
@XmlAccessorType(XmlAccessType.FIELD)
public class HealthProfile {	
	private double weight; // in kg
	private double height; // in m

	public HealthProfile(double weight, double height){		
		this.weight = weight;
		this.height = height;		
	}
	
	public HealthProfile(){}
		
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@XmlElement(name="bmi")
	public double getBMI() {	
		return Double.parseDouble(new DecimalFormat("##.##").format(this.weight/(Math.pow(this.height, 2)))); //Format the BMI, so it only has two digits of decimal
	}
}
