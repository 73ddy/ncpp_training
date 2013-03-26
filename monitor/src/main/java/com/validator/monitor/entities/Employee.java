package com.validator.monitor.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.validator.common.util.StringUtil;

/**
 * Employee entity. One of the entities that are to be validated.
 * 
 * @author gaurav1935
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Employee extends ValidableEntity {
	@XmlElement(name = "id", required = true)
	private Integer id;
	@XmlElement(name = "name", required = true)
	private String name;
	@XmlElement(name = "dateOfJoining", required = false)
	private Date dateOfJoining;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Override
	public String toString() {
		return StringUtil.concatenateStrings("Employee: id - ", id.toString(), ", name - ", name);
	}

	@Override
	public String getEntityFileName() {
		return StringUtil.concatenateStrings(this.name, "_", this.id.toString());
	}
}
