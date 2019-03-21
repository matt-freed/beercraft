package com.beercom.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class EJBNew {
	
	public String getString() {
		return "test";
	}

}
