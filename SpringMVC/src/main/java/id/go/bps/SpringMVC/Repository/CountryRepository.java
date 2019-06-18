package id.go.bps.SpringMVC.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import id.go.bps.SpringMVC.Model.Country;

@Repository
public class CountryRepository {
	private static final Map<String, Country> COUNTRIES=new HashMap<String, Country>();
	
	static {
		Country id  = new Country("ID","Indonesia");
		Country uk  = new Country("UK","United Kingdom");
		Country us  = new Country("US","United States");
		Country au  = new Country("AU","Australia");
		
		COUNTRIES.put(id.getKey(),id);
		COUNTRIES.put(uk.getKey(),uk);
		COUNTRIES.put(us.getKey(),us);
		COUNTRIES.put(au.getKey(),au);
	}
	
	public List<Country> listAllCountries(){
		List<Country> countries = new  ArrayList<Country>();
		countries.addAll(COUNTRIES.values());
		return countries;
	}

	public Map<String, Country> mapCountries(){
		return COUNTRIES;
	}
}
