package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.Counter;
import services.ActionAuthenticator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import javax.inject.Inject;
import javax.inject.Singleton;
import play.*;
import play.mvc.*;

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class contains an
 * action that shows an incrementing count to users. The {@link Counter}
 * object is injected by the Guice dependency injection system.
 */
@Singleton
public class CountController extends Controller {

    private final Counter counter;

    @Inject
    public CountController(Counter counter) {
       this.counter = counter;
    }

    /**
     * An action that responds with the {@link Counter}'s current
     * count. The result is plain text. This action is mapped to
     * <code>GET</code> requests with a path of <code>/count</code>
     * requests by an entry in the <code>routes</code> config file.
     */
    @Security.Authenticated(ActionAuthenticator.class)
    public Result count() {
        ObjectNode result = Json.newObject();
        result.put("current_count", Integer.toString(counter.nextCount()));
        return ok(result);
    }

}
