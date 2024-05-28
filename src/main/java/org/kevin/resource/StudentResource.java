package org.kevin.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.kevin.dto.StudentDto;
import org.kevin.entity.Student;
import org.kevin.repository.StudentRepository;
import org.kevin.service.StudentService;

import java.net.URI;
import java.util.List;

@Path("/student")
public class StudentResource {

    @Inject
    StudentService studentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentDto> getStudents() {
        return studentService.getAllStudents();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        if (studentDto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(studentDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeStudent(StudentDto studentDto) {
        StudentDto createdStudent = studentService.createStudent(studentDto);
        URI uri = UriBuilder.fromResource(StudentResource.class)
                .path("/{id}")
                .resolveTemplate("name", createdStudent.getName())
                .build();
        return Response.created(uri).entity(createdStudent).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") Long id, StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        if (updatedStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedStudent).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.ok().build();
    }

}
