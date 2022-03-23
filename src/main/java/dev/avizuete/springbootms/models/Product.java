package dev.avizuete.springbootms.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1713824400033229902L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String category;
	private String size;
	private String color;
	private String description;
	
}
  