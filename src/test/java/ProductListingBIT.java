import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.tw.cart.ProductInventory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ProductListingBIT {

    @Test
    @Ignore
    public void should_get_list_of_products_from_input_with_appropiate_count() throws IOException {
        Response inputProducts = given().
                header(new Header("userId", "HkqEYWZi-")).
                when().
                get("http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/input");

        ProductInventory productInventory = new ProductInventory(inputProducts.asString());

        int expectedSize = new ObjectMapper().readTree(inputProducts.asString()).size();
        assertEquals(expectedSize, productInventory.getAllProductCount());
    }

    @Test
    @Ignore
    public void should_get_list_of_products_from_input_and_post_appropiate_count() throws IOException {
        Header requestAuthHeader = new Header("userId", "HkqEYWZi-");
        Response inputProducts = given().
                header(requestAuthHeader).
                when().
                get("http://tw-http-hunt-api-1062625224.us-east-2.elb.amazonaws.com/challenge/input");

        ProductInventory productInventory = new ProductInventory(inputProducts.asString());

        Response post = given().
                header(requestAuthHeader).
                request().
                body("{\"count\": " + productInventory.getAllProductCount() + "}").
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
