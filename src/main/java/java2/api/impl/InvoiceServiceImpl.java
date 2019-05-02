package java2.api.impl;

// 418

//import java2.api.InvoiceService;

import java2.api.services.BaseService;
import java2.jpa.services.Dao;
import java2.api.requests.AddCartLineRequest;

import java2.jpa.entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

//@Path("/invoice")
//public class InvoiceServiceImpl extends BaseService<Invoice> {
//
//    @Context
//    private HttpServletRequest servletRequest;
//
//
//    @Override
//    protected Class<Invoice> getEntityClass() {
//        return Invoice.class;
//    }
//
//    @Override
//    public Response list(int size, int skip) {
//        HttpSession session = servletRequest.getSession();
//        if (session.getAttribute("user") != null) {
//            return super.list(size, skip);
//        }
//        return Response.status(Response.Status.UNAUTHORIZED).build();
//    }
//
//    @GET
//    @Path("/{id}/f")
//    public Response getFull(@PathParam("id") int id) {
//        Dao<Invoice> dao = createDao();
//
//
//        Invoice entity = dao.read(id, Invoice.GRAPH_LINE);
//
//        if (entity == null) return Response.status(Response.Status.NOT_FOUND).build();
//        return Response.ok(entity).build();
//    }
//
//    @POST
//    @Path("/{id}")
//    public Response addInvoice(@PathParam("id") int id, AddCartLineRequest addInvoiceLineRequest) {
//        Dao<Invoice> dao = createDao();
//
//        Invoice invoice = dao.read(id);
//        if (invoice == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        Dao<Product> productDao = new Dao<>(Product.class);
//        Product product = productDao.read(addInvoiceLineRequest.getId());
//        if (product == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        InvLine invLine = new InvLine();
//        invLine.setInvoice(invoice);
//        invLine.setQty(addInvoiceLineRequest.getQty());
//        invLine.setProduct(product);
//        invLine.setPrice(product.getPrice());
//
//
//        invoice.getLines().add(invLine);
//        invoice = dao.update(invoice);
//        return Response.ok(invoice).build();
//    }
//}
//
