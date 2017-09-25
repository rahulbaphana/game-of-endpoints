package com.tw.cart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.domain.Category;
import com.tw.domain.Product;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

public class ProductInventory {
    private Set<Product> products = emptySet();

    public ProductInventory(final String jsonResponseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.products = objectMapper.readValue(jsonResponseBody, new TypeReference<Set<Product>>() {
        });
    }

    public int getAllProductCount() {
        return products.size();
    }

    public long getActiveProductsCount(Date date) {
        return getActiveProducts(date).size();
    }

    private long getActiveProductsCount(Date date, Category category) {
        List<Product> filteredProducts = getActiveProducts(date).stream()
                                                               .filter(p -> p.belongsTo(category))
                                                               .collect(Collectors.toList());
        return filteredProducts.size();
    }

    private List<Product> getActiveProducts(Date date) {
        return products.stream().filter(p -> p.isActiveFor(date)).collect(Collectors.toList());
    }

    private Set<Category> getAllCategories() {
        return products.stream().map(Product::getCategory).collect(Collectors.toSet());
    }

    public Map<Category, Long> getActiveCountForCategoriesOn(Date date) {
        Set<Category> allCategories = getAllCategories();
        Map<Category, Long> activeProductsPerCategory = new HashMap<>();
        allCategories.stream()
                     .filter(c -> getActiveProductsCount(date, c) > 0)
                     .forEach(c -> activeProductsPerCategory.put(c, getActiveProductsCount(date, c)));

        return activeProductsPerCategory;
    }

    public int getTotalPriceForActiveProducts(Date date) throws ParseException {
        List<Product> activeProducts = getActiveProducts(date);
        Optional<Product> totalPrice = activeProducts.stream().reduce(Product::addPricesWith);
        return totalPrice.map(Product::getPrice).orElse(0);
    }
}
