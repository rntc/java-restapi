package com.rntc.api.service;

import java.util.regex.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rntc.api.exception.APIException;
import com.rntc.api.orm.GradeMapper;
import com.rntc.api.repository.GradeRepository;
import com.rntc.entity.Grade;
import com.rntc.entity.Page;

/**
 * 
 * @author reis
 * @since 05/21/2017
 *
 */

@Path("/grades")
public class GradeService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";

	/**
	 * @example /rest-grades/api/grades/?page=1
	 */
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("page") String page) throws APIException {

		GradeRepository repository = new GradeRepository();

		if ((page == null) || (page.isEmpty())) {
			return Response.ok(new Page(repository.getAll())).build();
		}

		if (page == "0") {
			throw new APIException(400, "The page can't be 0. Provide a value greater or equal 0.");
		}

		if (!Pattern.matches("^\\d+", page)) {
			throw new APIException(400, "A invalid value was provided as parameter.");
		}

		return Response.ok(new Page(repository.getByRange(Integer.parseInt(page)))).build();
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response get(@PathParam("id") int id) throws APIException {

		if (id == 0) {
			throw new APIException(400, "The id has to be greater or equals 1.");
		}

		GradeMapper mapper = new GradeMapper();

		Grade grade = new Grade();
		grade.setId(id);

		grade = mapper.select(grade);

		if (grade == null) {
			throw new APIException(204, "The grade doesn't exist.");
		}

		return Response.ok(grade).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Grade grade) throws APIException{
		GradeMapper mapper = new GradeMapper();
		
		if(grade.getDescription().isEmpty() || grade.getTitle().isEmpty()){
			throw new APIException(400, "One or more required parameters wasn't provided.");
		}
		
		mapper.insert(grade);
		
		return Response.ok(grade).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON)
	public Response put(Grade grade, @PathParam("id") int id) throws APIException {
		GradeMapper mapper = new GradeMapper();
		
		if(id <= 0){
			throw new APIException(400, "The grade id can't be less or equals zero.");
		}
		
		grade.setId(id);
		mapper.update(grade);
		
		return Response.ok(grade).build();
	}

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id)  throws APIException{
		GradeMapper mapper = new GradeMapper();
		
		Grade grade = new Grade(id, null, null);
		grade = mapper.delete(grade);
		return Response.ok(grade).build();
		
	}

}
