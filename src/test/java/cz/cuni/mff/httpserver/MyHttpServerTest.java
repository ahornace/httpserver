package cz.cuni.mff.httpserver;

import org.junit.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class MyHttpServerTest {

    @Path("/system")
    static class SystemsController {

        private static final String VERSION = "1.0";

        private final Set<String> systems = new HashSet<>(Set.of("integration", "prometheus", "grafana"));

        @GET
        @Path("/version")
        @Produces(MediaType.TEXT_PLAIN)
        public String version() {
            return VERSION;
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Set<String> getSystems() {
            return systems;
        }

        @PUT
        @Consumes(MediaType.TEXT_PLAIN)
        public void addSystem(final String system) {
            systems.add(system);
        }

    }

    @Test
    public void versionTest() throws IOException {
        MyHttpServer s = new MyHttpServer(6666);
        s.register(SystemsController.class);
        s.start();

        when().get("http://localhost:6666/systems/version")
                .then()
                .body(equalTo(SystemsController.VERSION));

        s.stop();
    }

    // TODO: add more tests

}