package datacoder.dropwizardtest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
 
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
 
 
@Path("/Products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductRESTController {
 
    private final Validator validator;
 
    public ProductRESTController(Validator validator) {
        this.validator = validator;
    }
 
    @GET
    public Response getProducts() {
        return Response.ok(ProductDB.getProducts()).build();
    }
 
    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Integer id) {
        Product Product = ProductDB.getProduct(id);
        if (Product != null)
            return Response.ok(Product).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }
 
    @POST
    public Response createProduct(Product Product) throws URISyntaxException {
        // validation
        Set<ConstraintViolation<Product>> violations = validator.validate(Product);
        Product e = ProductDB.getProduct(Product.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Product> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
        	ProductDB.updateProduct(Product.getId(), Product);
            return Response.created(new URI("/Products/" + Product.getId()))
                    .build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
 
    @PUT
    @Path("/{id}")
    public Response updateProductById(@PathParam("id") Integer id, Product Product) {
        // validation
        Set<ConstraintViolation<Product>> violations = validator.validate(Product);
        Product e = ProductDB.getProduct(Product.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Product> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            Product.setId(id);
            ProductDB.updateProduct(id, Product);
            return Response.ok(Product).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response removeProductById(@PathParam("id") Integer id) {
        Product Product = ProductDB.getProduct(id);
        if (Product != null) {
        	ProductDB.removeProduct(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
