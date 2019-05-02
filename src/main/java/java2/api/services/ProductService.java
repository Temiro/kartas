package java2.api.services;

//import java2.jpa.entities.Invoice;
import java2.jpa.entities.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductService extends BaseService<Product> {

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

//    @Override
//    public Response list(int size, int skip) {
//        return super.list(size, skip);
//    }
}
