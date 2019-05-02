package java2.api.services;


//import java2.api.services.BaseService;
import java2.api.requests.AddCartLineRequest;
import java2.jpa.entities.Cart;
import java2.jpa.entities.CartLine;
import java2.jpa.entities.Product;
import java2.jpa.services.Dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Optional;

@Path("/cart")
public class CartService extends BaseService<Cart> {

    // reikalaujama autorizacijos
    @Context
    private HttpServletRequest servletRequest;

    @Override
    protected Class<Cart> getEntityClass() {
        return Cart.class;
    }

    // CRUD

    // CREATE veikia be priekaistu
//    @POST
    @Override
    public Response add(Cart cart) {

        Dao<Cart> dao = createDao();
        dao.create(cart);

        return Response.ok(cart).build();
    }


    @POST
    @Path("/add")
    public Response addCartLine(AddCartLineRequest addCartLineRequest) {

        Dao<Product> productDao = new Dao<>(Product.class);
        Product product = productDao.read(addCartLineRequest.getId());
        if (product == null) return Response.status(Response.Status.NOT_FOUND).build();

        HttpSession session = servletRequest.getSession();

        Object obj = session.getAttribute("cart");
        Cart cart;
        if (obj instanceof Cart) {
            cart = (Cart) obj;
        } else {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        if (cart.getCartLines() == null) cart.setCartLines(new HashSet<>());

        Optional<CartLine> line = cart.getCartLines().stream()
                .filter(x -> x.getProduct().getId() == addCartLineRequest.getId())
                .findFirst();

        if (line.isPresent()) {
            CartLine cartLine = line.get();
            cartLine.setQty(cartLine.getQty() + addCartLineRequest.getQty());
        } else {
            CartLine cartLine = new CartLine();
            cartLine.setId(cart.getCartLines().size() + 1); // coment on DB
            cartLine.setProduct(product);
            cartLine.setQty(addCartLineRequest.getQty());
            cart.getCartLines().add(cartLine);
        }

        return Response.ok(cart).build();
    }

    // CREATE cartLine by cart id

    /**
     * per parametra gauname krepselio cartId
     * per objekta(web/json) gauname prekes productId ir qty
     * 1
     * nuskaitome krepseli cart paga cartId
     * 2
     * nuskaitome preke product paga productId
     * todo
     * 3
     * tikriname ar tokia preke jau yra krepselyje,
     * - jei taip - cartLine.qty++
     * - jei ne - sukuriame nauja CartLine obj cartLine
     * -- i cartLine pridedame product
     * -- i cartLine pridedame qty
     * dedame cartLine į krepselio prekiu list
     * 4
     * update cartLine
     */
    @POST
    @Path("/{id}")
    public Response addCartLine(@PathParam("id") int cartId, AddCartLineRequest addCartLineRequest) {

        int productId = addCartLineRequest.getId();
        int qty = addCartLineRequest.getQty();

        try (Dao<Cart> cartDao = createDao()) {

            // 1
            Cart cart = cartDao.read(cartId);
            if (cart == null)
                // jeigu cart=null sukurti nauja
                return Response.status(Response.Status.NOT_FOUND).build();

            // 2
            Dao<Product> productDao = new Dao<>(Product.class);
            Product product = productDao.read(productId);
            if (product == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            // 3 patikrinti ar cart.crtLine turi tokia preke
            boolean isCartLine = false;
            for (CartLine cartLine : cart.getCartLines()) {

                // jei turi pridėti qty
                if (cartLine.getProduct().getId() == product.getId()) {

                    cartLine.setQty(cartLine.getQty() + qty);
                    isCartLine = true;
                    break;
                }
            }

            // jei neturi sukurti nauja cartLine ir paduoti i cart
            if (!isCartLine) {

                CartLine cartLine = new CartLine();
                cartLine.setCart(cart);
                cartLine.setQty(qty);
                cartLine.setProduct(product);
                cart.getCartLines().add(cartLine);
            }

            cart = cartDao.update(cart);
            return Response.ok(cart).build();
        }
    }

//    @POST
//    @Path("/{id}")
//    public Response addCartLine(@PathParam("id") int id, AddCartLineRequest cartLineRequestQty) {
//
//        // 1
//        Dao<Cart> cartDao = createDao();
//        Cart cart = cartDao.read(id);
//        if (cart == null)
//            return Response.status(Response.Status.NOT_FOUND).build();
//
//        // 2
//        Dao<Product> productDao = new Dao<>(Product.class);
//        Product product = productDao.read(cartLineRequestQty.getId());
//        if (product == null)
//            return Response.status(Response.Status.NOT_FOUND).build();
//
//        // 3
//        CartLine cartLine = new CartLine();
//        cartLine.setCart(cart);
//        cartLine.setQty(cartLineRequestQty.getQty());
//        cartLine.setProduct(product);
////        cartLine.setPrice(product.getPrice());
//        cart.getLines().add(cartLine);
//
//        // 4
//        cart = cartDao.update(cart);
//
//        return Response.ok(cart).build();
//    }
//


    @GET // READ_LIST
    @Path("/list")
    public Response list(@QueryParam("size") @DefaultValue("10") int size, @QueryParam("skip") @DefaultValue("0") int skip) {

        Dao<Cart> dao = createDao();
        return Response.ok().entity(dao.list(size, skip)).build();
    }

    // gauti full list
    @GET
    @Path("/{id}/f")
    public Response getFull(@PathParam("id") int id) {

        try (Dao<Cart> dao = createDao()) {
            Cart entity = dao.read(id, Cart.GRAPH_CART_LINES);

            if (entity == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(entity).build();
        }
    }

    // gauti full list
    @GET
    public Response getCart() {

        HttpSession session = servletRequest.getSession();
        return Response.ok(session.getAttribute("cart")).build();

    }

    @DELETE
    @Path("/deleteCart/{id}")
    public Response deleteCart(@PathParam("id") int id){
        HttpSession session = servletRequest.getSession();

        Object obj = session.getAttribute("cart");
        Cart cart;
        if (obj instanceof Cart) {
            cart = (Cart) obj;
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<CartLine> cartLine=cart.getCartLines().stream()
                .filter(x->x.getProduct().getId()==id)
                .findFirst();

        cart.getCartLines().remove(cartLine);

        return Response.ok().build();

    }

}
