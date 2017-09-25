import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.tw.cart.ProductInventory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

public class ActiveProductsForCategoriesBIT {
    @Test
    @Ignore
    public void should_get_list_of_products_from_input_with_appropiate_count() throws IOException {
        Header requestAuthHeader = new Header("userId", "HkqEYWZi-");
        Response inputProducts = given().
                header(requestAuthHeader).
                when().
                get("http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/input");

        ProductInventory productInventory = new ProductInventory(inputProducts.asString());

        Response post = given().
                header(requestAuthHeader).
                request().
                body(productInventory.getActiveCountForCategoriesOn(new Date())).
                contentType("application/json; charset=utf-8").
                when().
                post("http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/output");

        inputProducts.prettyPrint();
        post.
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    @Ignore
    public void should_get_total_of_active_products_price_from_input() throws IOException, ParseException {
        Header requestAuthHeader = new Header("userId", "HkqEYWZi-");
        Response inputProducts = given().
                header(requestAuthHeader).
                when().
                get("http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/input");

        ProductInventory productInventory = new ProductInventory(inputProducts.asString());

        Response post = given().
                header(requestAuthHeader).
                request().
                body("{\"totalValue\": " +  productInventory.getTotalPriceForActiveProducts(new Date())+ " }").
                contentType("application/json; charset=utf-8").
                when().
                post("http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/output");

        inputProducts.prettyPrint();
        post.
                then().
                assertThat().
                statusCode(200);
    }
}
