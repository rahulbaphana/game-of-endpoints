import com.tw.cart.ProductInventory;
import com.tw.domain.Category;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ProductListingTest {

    @Test
    public void should_count_the_list_of_products_provided_by_tw_endpoint() throws IOException {
        ProductInventory productInventory = new ProductInventory("[\n" +
                "    {\n" +
                "        \"price\": 629,\n" +
                "        \"name\": \"Homefab India Set of 2 Royal Silky Aqua Blue Designer Curtains (HF158)\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"price\": 499,\n" +
                "        \"name\": \"Homefab India Set of 2  Beautiful Marble Plain Black Curtains (HF342)\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"price\": 350,\n" +
                "        \"name\": \"Set of 2 - measuring cups & measuring spoon\"\n" +
                "    }\n" +
                "]");


        assertEquals(3, productInventory.getAllProductCount());
    }

    @Test
    public void should_count_the_active_products() throws IOException, ParseException {
        ProductInventory productInventory = new ProductInventory("[\n" +
                "            {\n" +
                "                \"endDate\": \"2017-04-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 260,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"Stainless Steel Cutter Peeler Tool Pineapple Seed Clip Home Kitchen Gadgets\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": \"2017-12-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 149,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"20.5cm Fruit Cutter Chef Kitchen Cutlery Knife Knives Choice - 07\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 1737,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"LETV LeEco Le 2 32GB Rose Gold\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 999,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"Nokia 1100\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 499,\n" +
                "                \"category\": \"Furniture\",\n" +
                "                \"name\": \"Homefab India Set of 2  Beautiful Marble Plain Black Curtains (HF342)\"\n" +
                "            }\n" +
                "        ]");

        Date refDate = getDateFor("2017-09-23");
        assertEquals(2, productInventory.getActiveProductsCount(refDate));
    }

    @Test
    public void should_get_active_products_for_day_as_per_category() throws IOException, ParseException {
        ProductInventory productInventory = new ProductInventory("[\n" +
                "            {\n" +
                "                \"endDate\": \"2017-04-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 260,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"Stainless Steel Cutter Peeler Tool Pineapple Seed Clip Home Kitchen Gadgets\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": \"2017-12-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 149,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"20.5cm Fruit Cutter Chef Kitchen Cutlery Knife Knives Choice - 07\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 1737,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"LETV LeEco Le 2 32GB Rose Gold\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 999,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"Nokia 1100\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 499,\n" +
                "                \"category\": \"Furniture\",\n" +
                "                \"name\": \"Homefab India Set of 2  Beautiful Marble Plain Black Curtains (HF342)\"\n" +
                "            }\n" +
                "        ]");

        Date date = getDateFor("2017-09-23");
        assertEquals(1, productInventory.getActiveProductsCount(date, new Category("Electronics")));
        assertEquals(1, productInventory.getActiveProductsCount(date, new Category("Kitchen")));
    }

    @Test
    public void should_get_active_count_per_category() throws IOException, ParseException {
        ProductInventory productInventory = new ProductInventory("[\n" +
                "            {\n" +
                "                \"endDate\": \"2017-04-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 260,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"Stainless Steel Cutter Peeler Tool Pineapple Seed Clip Home Kitchen Gadgets\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": \"2017-12-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 149,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"20.5cm Fruit Cutter Chef Kitchen Cutlery Knife Knives Choice - 07\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 1737,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"LETV LeEco Le 2 32GB Rose Gold\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 999,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"Nokia 1100\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 499,\n" +
                "                \"category\": \"Furniture\",\n" +
                "                \"name\": \"Homefab India Set of 2  Beautiful Marble Plain Black Curtains (HF342)\"\n" +
                "            }\n" +
                "        ]");


        Map<Category, Long> availableCategories = new HashMap<Category, Long>(){{
            put(new Category("Electronics"), 1l);
            put(new Category("Kitchen"), 1l);
        }};
        assertEquals(availableCategories, productInventory.getActiveCountForCategoriesOn(getDateFor("2017-09-23")));
    }

    @Test
    public void should_get_the_total_price_of_active_products() throws IOException, ParseException {
        ProductInventory productInventory = new ProductInventory("[\n" +
                "            {\n" +
                "                \"endDate\": \"2017-04-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 260,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"Stainless Steel Cutter Peeler Tool Pineapple Seed Clip Home Kitchen Gadgets\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": \"2017-12-04\",\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 149,\n" +
                "                \"category\": \"Kitchen\",\n" +
                "                \"name\": \"20.5cm Fruit Cutter Chef Kitchen Cutlery Knife Knives Choice - 07\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2017-01-30\",\n" +
                "                \"price\": 1737,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"LETV LeEco Le 2 32GB Rose Gold\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 999,\n" +
                "                \"category\": \"Electronics\",\n" +
                "                \"name\": \"Nokia 1100\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"endDate\": null,\n" +
                "                \"startDate\": \"2018-01-30\",\n" +
                "                \"price\": 499,\n" +
                "                \"category\": \"Furniture\",\n" +
                "                \"name\": \"Homefab India Set of 2  Beautiful Marble Plain Black Curtains (HF342)\"\n" +
                "            }\n" +
                "        ]");

        assertEquals(1886, productInventory.getTotalPriceForActiveProducts(getDateFor("2017-09-23")));
    }

    private Date getDateFor(String now) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(now);
    }
}
