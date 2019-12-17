package com.ngs.REST_Jersey_MySql_Repository;

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




/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	PersonRepository repo=new PersonRepository();
    @GET
    @Path("getpersons")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getPersons() {
        return repo.getPersons();
    }
    @GET
	@Path("person/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Person getPerson(@PathParam("id") int id) {
		return repo.getPerson(id);
	}
	
	@POST
	@Path("addperson")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Person createPerson(Person p1) {
		System.out.println(p1);
		repo.create(p1);
		return p1;
	}
	

	@PUT
	@Path("updateperson")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Person updatePerson(Person p1) {
		if(repo.getPerson(p1.getId()).getId()==0){
			repo.create(p1);
		}
		else {
		System.out.println(p1);
		repo.update(p1);
		}
		return p1;
	}
	@DELETE
	@Path("deleteperson/{id}")
	public String deletePerson(@PathParam("id") int id) {
		
		repo.delete(id);
		System.out.println("Record is deleted succefully!");
		return "Record is deleted succefully";
		
	}
	
	
}