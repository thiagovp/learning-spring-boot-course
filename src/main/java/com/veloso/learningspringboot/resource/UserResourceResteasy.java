package com.veloso.learningspringboot.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.Optional;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.veloso.learningspringboot.model.User;
import com.veloso.learningspringboot.service.UserService;

@Component
@Path("api/v1/users")
public class UserResourceResteasy {
	
	private UserService userService;

	@Autowired
	public UserResourceResteasy(UserService userService) {
		this.userService = userService;
	}
	
	@GET
	@Produces(APPLICATION_JSON)
	public List<User> fetchUsers(@QueryParam("gender") String gender) {
		return userService.getAllUsers(Optional.ofNullable(gender));
	}
	
	@PUT
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response updateUser(@RequestBody User user) {
		int result = userService.updateUser(user);
		return getIntegerResponseEntity(result);
	}
	
	@DELETE
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	@Path("{userUid}")
	public Response deleteUser(@PathParam("userUid") UUID userUid) {
		int result = userService.removeUser(userUid);
		return getIntegerResponseEntity(result);
	}
	@GET
	@Produces(APPLICATION_JSON)
	@Path("{userUid}")
	public Response fetchUser(@PathParam("userUid") UUID userUid) {
		return userService.getUser(userUid).map(Response::ok).orElseGet(() -> Response
				.status(Status.NOT_FOUND).entity(new ErrorMessage("user " + userUid + " was not found."))).build();
	}

	@POST
	@Consumes(APPLICATION_JSON)
	@Produces(APPLICATION_JSON)
	public Response insertNewUser(@RequestBody User user) {
		int result = userService.insertUser(user);
		return getIntegerResponseEntity(result);
	}

	private Response getIntegerResponseEntity(int result) {
		if (result == 1) {
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	

}
