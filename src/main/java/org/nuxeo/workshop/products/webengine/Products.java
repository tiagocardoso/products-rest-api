/**
 * 
 */

package org.nuxeo.workshop.products.webengine;

import net.sf.json.JSONObject;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.ModuleRoot;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.workshop.products.ProductService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * The root entry for the WebEngine module. 
 * @author inevo
 */
@Path("/products")
@Produces("application/json;charset=UTF-8")
@WebObject(type="Products")
public class Products extends ModuleRoot {

    @GET
    @Path("computePrice/{id}/{country}")
    @Produces("application/json;charset=UTF-8")
    public Object getComputePrice(@PathParam("id") String id, @PathParam("country") String country) {
        JSONObject json = new JSONObject();
        DocumentModel product = ctx.getCoreSession().getDocument(new IdRef(id));
        ProductService productService = Framework.getService(ProductService.class);
        json.put("price", productService.computePriceWithTax(product, country));
        return Response.ok(json.toString(2)).type("application/json").build();
    }

    @GET
    @Path("computePrice/{id}")
    @Produces("application/json;charset=UTF-8")
    public Object getComputePriceWithTax(@PathParam("id") String id) {
        JSONObject json = new JSONObject();
        DocumentModel product = ctx.getCoreSession().getDocument(new IdRef(id));
        ProductService productService = Framework.getService(ProductService.class);
        json.put("price", productService.computePrice(product));
        return Response.ok(json.toString(2)).type("application/json").build();
    }
}
