package com.amitpar.restapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.amitpar.restapi.database.DatabaseClass;
import com.amitpar.restapi.model.Profile;

public class ProfileService {
 
	private Map<String, Profile> profiles=DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("amitpar", new Profile(1L, "amitpar", "Amit", "Par"));
		profiles.put("Thor", new Profile(2L, "Thor", "Thor", "OdinSon"));
	}
	      public List<Profile> getAllProfiles(){
			  return new ArrayList<Profile>(profiles.values());
		  }
		  
		  public Profile getProfile(String profileName) {
			  return profiles.get(profileName);
		  }
		  
		  public Profile addProfile(Profile profile) {
			  profile.setId(profiles.size()+1);
			  profiles.put(profile.getProfileName(), profile);
			  return profile;
		  }
		  
		  public Profile removeProfile(String profileName) {
			  //System.out.println("In remove method "+messages.toString());
			  System.out.println("In Remove profile method "+profileName);
			  return profiles.remove(profileName);
		  }
		  
		  public Profile updateProfile(Profile profile) {
			  if(profile.getProfileName().isEmpty()) {
				  return null;
			  }
			  profiles.put(profile.getProfileName(), profile);
			  return profile;
		  }
}
