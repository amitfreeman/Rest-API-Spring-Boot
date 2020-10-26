package com.amitpar.restapi.service;

import java.util.*;

import com.amitpar.restapi.database.DatabaseClass;
import com.amitpar.restapi.model.Message;

public class MessageService {
	
	 private Map<Long, Message> messages= DatabaseClass.getMessages();
	 
	 public MessageService() {
		messages.put(1L, new Message(1L, "Valkyrie", "Thor")); 
		messages.put(2L, new Message(2L, "Beer", "Freya")); 
	 }
 
	  public List<Message> getAllMessages(){
		  System.out.println("Get all message service called");
		  return new ArrayList<Message>(messages.values());
	  }
	  
	  /* paginated, filtered results */
	  public List<Message> getAllMessagesForYear(int year){
		  List<Message> messagesYear = new ArrayList<>();
		  Calendar cal = Calendar.getInstance();
		  for(Message message : messages.values()) {
			  cal.setTime(message.getCreated());
			  if(cal.get(Calendar.YEAR) == year) {
				  messagesYear.add(message);
			  }
		  }
		  return messagesYear;
	  }
	  
	  public List<Message> getAllMessagesPaginated(int start, int size){
		  ArrayList<Message> list= new ArrayList<Message>(messages.values());
		  if(start+size > list.size()) return new ArrayList<Message>();
		  return list.subList(start, start+size);
	  }
	  /******** end  ******/
	  
	  public Message getMessage(long id) {
		  return messages.get(id);
	  }
	  
	  public Message addMessage(Message message) {
		  message.setId(messages.size()+1);
		  messages.put(message.getId(), message);
		  return message;
	  }
	  public Message removeMessage(long id) {
		  //System.out.println("In remove method "+messages.toString());
		  if(id <= 0) {
			  return null;
		  }
		  System.out.println("In Remove method "+id);
		  return messages.remove(id);
	  }
	  
	  public Message updateMessage(Message message) {
		  if(message.getId() <= 0) {
			  return null;
		  }
		  messages.put(message.getId(), message);
		  return message;
	  }
	  

}
