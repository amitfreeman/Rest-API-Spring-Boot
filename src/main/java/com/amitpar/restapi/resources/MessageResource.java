package com.amitpar.restapi.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.amitpar.restapi.service.*;
import com.amitpar.restapi.model.*;

@Path("/messages")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) 
public class MessageResource {
	
	   MessageService messageService=new MessageService(); 

	  //get all messages
	  @GET
	  public List<Message> getMessages(@QueryParam("year") int year,
			                          @QueryParam("start") int start,
			                          @QueryParam("size") int size ){
		  //return "Bhag na chutiye";
		 // List<Message> listM;
		  System.out.println("Message Resource called");
		  if(year>0) {
			   return messageService.getAllMessagesForYear(year);
		  }
		  if(start>0 || size>0) {
			  return messageService.getAllMessagesPaginated(start, size);
		  }
		  else {
			  return  messageService.getAllMessages();
		  }
		  
		  /*return Response.status(Status.OK)
				  .entity(listM)
				  .build();*/
	  }
	  
	 //get message by ID
	  @GET
	  @Path("/{messageId}")
	  //public Message getMessage(@PathParam("messageId") long Id) {
	  public Response getMessage(@PathParam("messageId") long Id,
			  @Context UriInfo uriInfo) {
		  //return messageService.getMessage(Id);
		  Message message=messageService.getMessage(Id);
		  return Response.ok(uriInfo.getAbsolutePathBuilder().path(String.valueOf(Id)).build())
				  .entity(message)
				  .build();
	  }

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri=(uriInfo.getBaseUriBuilder()
				  .path(MessageResource.class)
				  .path(MessageResource.class, "getCommentResource")
				  .path(CommentResource.class))	
				  .build()
				  .toString();
		return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
				  .path(MessageResource.class)
				  .path(String.valueOf(message.getId()))
				  .build()
				  .toString();
		return uri;
	}
	  
	  //create new message, POST
	  @POST
	  //public Message addMessage(Message newMsg) {
	  public Response addMessage(Message newMsg,
			                      @Context UriInfo uriInfo) {
		  Message newMessage=messageService.addMessage(newMsg);
		  newMessage.addLink( getUriForSelf(uriInfo, newMessage), "self");
		  newMessage.addLink( getUriForProfile(uriInfo, newMessage), "profile");
		  newMessage.addLink( getUriForComments(uriInfo, newMessage), "comments");
		  String newId=String.valueOf(newMessage.getId());
		  URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		  return Response.created(uri)
		           .entity(newMessage)
		           .build();
		//return messageService.addMessage(newMsg);
	  }
	  
	  //update message
	  @PUT
	  @Path("/{messageId}")
	  public Message updateMessage(@PathParam("messageId") long Id,Message message) {
		  message.setId(Id);
		  return messageService.updateMessage(message);
	  }
	  
	  //Delete message using id
	  @DELETE
	  @Path("/{messageId}")
	  public Message deleteMessage(@PathParam("messageId") long Id) {
		  return messageService.removeMessage(Id);
	  }
	  
	  //Sub Rsource to comments
	  @Path("/{messageId}/comments")
	  public CommentResource getCommentResource() {
		  return new CommentResource();
	  }

}
