package com.dbs.springmvcapp.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Entity
@Table
@XmlRootElement
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @XmlElement
    private String name;
    @XmlElement
    private String lastname;
    private String password;
    @XmlElement
    private String email;
    @XmlElement
    private String empcode;
    @Range(min = 18, max = 58, message = "Employee age should be between 18 and 58")
    private int age;
    @XmlElement
    private LocalDate dateOfBirth;
    public Employee(){
    	
    }
	public Employee(long id, String name, String lastname, String email, String empcode) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.empcode = empcode;
	}
	
	public void setDateOfBirth(String dateOfBirth){
	       this.dateOfBirth =  LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    }
	    public String getDateOfBirth(){
	        if(this.dateOfBirth != null) {
	            return this.dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	        }
	        return "";
	    }
}