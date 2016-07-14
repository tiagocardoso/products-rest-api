/**
 * 
 */

package org.nuxeo.workshop.products.webengine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.ModuleRoot;


/**
 * The root entry for the WebEngine module. 
 * @author inevo
 */
@Path("/products")
@Produces("text/html;charset=UTF-8")
@WebObject(type="Products")
public class Products extends ModuleRoot {

    @GET
    public Object doGet() {
        return getView("index");
    }

}
