package com.java.test.xml;

import java.util.Iterator;
import java.util.List;

public class Persons {
	private String type; 
    private List<Person> listPerson; 

    public Persons(List<Person> listPerson, String type) { 
        this.listPerson = listPerson; 
        this.type = type; 
    } 

    public String getType() { 
        return type; 
    } 

    public void setType(String type) { 
        this.type = type; 
    } 

    public List<Person> getListPerson() { 
        return listPerson; 
    } 

    public void setListPerson(List<Person> listPerson) { 
        this.listPerson = listPerson; 
    } 

    @SuppressWarnings("all")
	public String toString() { 
        StringBuffer sb=new StringBuffer(); 
        for(Iterator it=listPerson.iterator();it.hasNext();){ 
            Person p=(Person)it.next(); 
            sb.append(it.toString()); 
        } 
        return "Persons{" + 
                "type='" + type + '\'' + 
                ", listPerson=" + sb.toString() + 
                "}\n"; 
    } 
}
