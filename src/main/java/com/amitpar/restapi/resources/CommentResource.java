package com.amitpar.restapi.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.amitpar.restapi.model.Comment;
import com.amitpar.restapi.service.CommentService;


@Path("/")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML}) 
public class CommentResource {
	
	private CommentService commentService=new CommentService();
   
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId,
			                 @PathParam("commentId") long commentId) {
		return commentService.getAllComment(messageId, commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentID}")
	public Comment updateComment(@PathParam("messageId") long messageId,
			                     @PathParam("commentId") long commentId,
			                    Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentID}")
	public void deleteComment(@PathParam("messageId") long messageId,
			                     @PathParam("commentId") long commentId) {
		commentService.deleteComment(messageId, commentId);
	}
}
