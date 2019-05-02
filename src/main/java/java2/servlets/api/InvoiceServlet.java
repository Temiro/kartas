//package java2.servlets.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import java2.jpa.services.Dao;
//import java2.jpa.helpers.EntityManagerHelper;
//import java2.jpa.entities.Invoice;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet("/a/invoice")
//public class InvoiceServlet extends HttpServlet {
//    // get = read
//    // post = create
//    // put = update
//    // delete = delete
//    // tik naršyklėje ima duomenis
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // gauname ne tiesiogiai json, o greičiausiai html arba text
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("utf-8");
//
//        String id = req.getParameter("id");
//
////        PrintWriter writer = resp.getWriter();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new Hibernate5Module());
//        mapper.registerModule(new JavaTimeModule());
//        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
////        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false);
//
//
//        if (id == null) {
////            writer.println("{\"error\": \"Nenurodytas id\"}");
//            Response error = new Response("Nenurodytas id", 100);
//            mapper.writeValue(resp.getWriter(), error);
//            return;
//        }
//
//        Dao<Invoice> invoiceDao = new Dao<>(EntityManagerHelper.getEntityManager());
//        Invoice invoice = invoiceDao.read(Invoice.class, Integer.valueOf(id));
//        if (invoice == null) {
////            writer.println("Invoice su tokiu id " + id + " nerastas");
//            Response error = new Response("Invoice su tokiu id " + id + " nerastas", 110);
//            mapper.writeValue(resp.getWriter(), error);
//        } else {
////            writer.println(invoice.getDate() + " " + invoice.getNumber());
//            Response data = new Response(invoice);
//            mapper.writeValue(resp.getWriter(), data);
//        }
//    }
//}
////doPost reiskia sukurti nauja
////doPut - update
//
////gsonas turi anotacijas, kad serializuotu tik i viena puse
////kad nebutu taip kad traukiam saskaita, saskaita turi koientus, o klientai saskaitas, ir serializuoja viska be [pabaigos
////@Expose