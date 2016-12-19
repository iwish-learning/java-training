package com.java.test.xml;

import java.util.Iterator;
import java.util.List;

public class Addresses {
	 private List<Address> listAdd; 

	    public Addresses(List<Address> listAdd) { 
	        this.listAdd = listAdd; 
	    } 

	    public List<Address> getListAdd() { 
	        return listAdd; 
	    } 

	    public void setListAdd(List<Address> listAdd) { 
	        this.listAdd = listAdd; 
	    } 

	    @SuppressWarnings("rawtypes")
		public String toString() { 
	        StringBuffer sb=new StringBuffer(); 
	        for(Iterator it=listAdd.iterator();it.hasNext();){ 
	            Address add=(Address)it.next(); 
	            sb.append(add.toString()); 
	        } 
	        return "Addresses{" + 
	                "listAdd=" + sb.toString() + 
	                "}\n"; 
	    } 
}
