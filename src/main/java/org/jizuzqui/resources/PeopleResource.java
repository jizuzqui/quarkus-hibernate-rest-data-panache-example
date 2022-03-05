package org.jizuzqui.resources;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.jizuzqui.entities.Person;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

public class PeopleResource { // The actual class name is going to be unique
    @Inject
    PeopleEntityResource resource;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Person get(@PathParam("id") Long id){
        Person person = resource.get(id);
        if (person == null) {
            throw new WebApplicationException(404);
        }
        return person;
    }

/*     @GET
    @Produces("application/json")
    public Response list(@QueryParam("sort") List<String> sortQuery,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("20") int pageSize) {
        Page page = Page.of(pageIndex, pageSize);
        Sort sort = getSortFromQuery(sortQuery);
        List<Person> people = resource.list(page, sort);
        // ... build a response with page links and return a 200 response with a list
    }
 */
    @Transactional
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Person personToSave) {
        Person person = resource.add(personToSave);
        return Response.ok(person).build();
    }

    @Transactional
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") Long id, Person personToSave) {
        if (resource.get(id) == null) {
            Person person = resource.update(id, personToSave);
            return Response.status(204).build();
        }
        return Response.notModified().build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        if (!resource.delete(id)) {
            throw new WebApplicationException(404);
        }
    }
}