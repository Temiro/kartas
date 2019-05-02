package java2.api;

import java2.api.filters.AuthenticationFilter;

import java2.api.impl.ProducrServiceImpl;
import java2.api.services.CartService;
import java2.api.services.ProductService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App() {
        register(ObjectMapperContextResolver.class);
        register(ProductService.class);
        register(CartService.class);
//        register(UserService.class);
//        register(AuthenticationFilter.class);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}

