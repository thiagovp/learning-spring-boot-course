package com.veloso.learningspringboot.clientproxy;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.RequestBody;

import com.veloso.learningspringboot.model.User;

public interface UserResourceV1 {

	@GET
	@Produces(APPLICATION_JSON)
	List<User> fetchUsers(@QueryParam("gender") String gender);

	@PUT
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	void updateUser(@RequestBody User user);

	@DELETE
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@Path("{userUid}")
	void deleteUser(@PathParam("userUid") UUID userUid);

	@GET
	@Produces(APPLICATION_JSON)
	@Path("{userUid}")
	User fetchUser(@PathParam("userUid") UUID userUid);

	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	void insertNewUser(@RequestBody User user);

}
