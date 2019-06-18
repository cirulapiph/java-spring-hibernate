package id.go.bps.SpringMVC.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import id.go.bps.SpringMVC.Model.Gender;

@Repository
public class GenderRepository {
    private static final Map<String, Gender> GENDER=new HashMap<String, Gender>();
	
	static {
		Gender male  = new Gender("M","lang.male");
		Gender female  = new Gender("F","lang.female");
		
        GENDER.put(male.getKey(),male);
        GENDER.put(female.getKey(),female);
	}
	
	public List<Gender> listGenders(){
		List<Gender> genders = new  ArrayList<Gender>();
		genders.addAll(GENDER.values());
		return genders;
	}

	public Map<String, Gender> mapGenders(){
		return GENDER;
	}
}