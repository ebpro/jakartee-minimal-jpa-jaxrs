package fr.univtln.bruno.samples.jakartaee.jpajaxrs;

import fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository.Message;
import fr.univtln.bruno.samples.jakartaee.jpajaxrs.repository.MessageDAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@Path("messages")
@RequestScoped
public class MessageResource {
	@Inject
	private MessageDAO messageDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response persist(Message message) {
		log.info("REST request to persist message : {}", message);
		messageDAO.create(message);
		return Response.ok(message).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Message message) {
		log.info("REST request to update message : {}", message);
		messageDAO.update(message);
		return Response.ok(message).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> findAll() {
		log.info("REST request to get all messages");
		return messageDAO.findAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response find(@PathParam("id") UUID id) {
		log.info("REST request to get message : {}", id);
		Message person = messageDAO.find(id);
		if (person == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			return Response.ok(person).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") UUID id) {
		log.info("REST request to delete Person : {}", id);
		messageDAO.delete(messageDAO.find(id));
		return Response.ok().build();
	}
}