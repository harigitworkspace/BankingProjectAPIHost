package com.bank.binding;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="COURSE_DTLS")
public class Course {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cid;
	private String name;
	private BigDecimal price;
	
	
	  // Getter and Setter methods for all fields
    public Long getCid() {
        return cid;
    }
    
    public void setCid(Long cid) {
        this.cid = cid;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    


}
