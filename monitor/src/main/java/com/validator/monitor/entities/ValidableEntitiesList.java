package com.validator.monitor.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contains the list of all the entities to be validates against the given set
 * of rules.
 * 
 * @author gaurav1935
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validableEntities")
public class ValidableEntitiesList {

	@XmlElementWrapper(name = "entityList")
	@XmlElements({ @XmlElement(name = "employeee", type = Employee.class) })
	private List<ValidableEntity> entityList;

	public List<ValidableEntity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<ValidableEntity> entityList) {
		this.entityList = entityList;
	}
}
