package java2.api.impl;

import java2.api.services.BaseService;
import java2.jpa.entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

//@Path("/invoice")
    @Path("/product")
public class ProducrServiceImpl extends BaseService<Product> {

       @Context
       private HttpServletRequest servletRequest;

       @Override
       protected Class<Product> getEntityClass() {
           return Product.class;
       }

       @Override
       public Response list(int size, int skip) {
           HttpSession session = servletRequest.getSession();
           if (session.getAttribute("user") != null){
               return super.list(size, skip);
           }
           return Response.status(Response.Status.UNAUTHORIZED).build();
       }
    }


    //package lt.bta.java2.api.services;
    //
    //import lt.bta.java2.jpa.entities.Product;
    //
    //import javax.servlet.http.HttpServletRequest;
    //import javax.servlet.http.HttpSession;
    //import javax.ws.rs.*;
    //import javax.ws.rs.core.Context;
    //import javax.ws.rs.core.Response;
    //
    //@Path("/product")
    //public class ProductService extends BaseService<Product>{
    //
    //   @Context
    //   private HttpServletRequest servletRequest;
    //
    //   @Override
    //   protected Class<Product> getEntityClass() {
    //       return Product.class;
    //   }
    //
    //   @Override
    //   public Response list(int size, int skip) {
    //       HttpSession session = servletRequest.getSession();
    //       if (session.getAttribute("user") != null){
    //           return super.list(size, skip);
    //       }
    //       return Response.status(Response.Status.UNAUTHORIZED).build();
    //   }
    //}

