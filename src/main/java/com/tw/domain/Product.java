package com.tw.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static java.lang.Long.MAX_VALUE;

public class Product {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(YYYY_MM_DD);

    private final Date endDate;
    private final Date startDate;
    private final int price;
    private final String name;
    private final Category category;

    public Product(@JsonProperty("price") final int price,
                   @JsonProperty("name") final String name) throws ParseException {
        this(null, null, price, null, name);
    }

    public Product(@JsonProperty("endDate") String endDate,
                   @JsonProperty("startDate") String startDate,
                   @JsonProperty("price") int price,
                   @JsonProperty("category") String category,
                   @JsonProperty("name") String name) throws ParseException {
        this.endDate = endDate!= null ? SIMPLE_DATE_FORMAT.parse(endDate) : new Date(MAX_VALUE);
        this.startDate = startDate!=null ? SIMPLE_DATE_FORMAT.parse(startDate) : new Date(MAX_VALUE);
        this.price = price;
        this.category = new Category(category);
        this.name = name;
    }

    public boolean isActiveFor(String date) throws ParseException {
        Date dateToCheck = SIMPLE_DATE_FORMAT.parse(date);
        return isActiveFor(dateToCheck);
    }

    public boolean isActiveFor(Date date) {
        return (!date.before (startDate) && !date.after (endDate));
    }

    public boolean belongsTo(Category category) {
        return category.equals(this.category);
    }

    public Category getCategory() {
        return category;
    }

    public Product addPricesWith(Product thatProduct) {
        try {
            return new Product((this.price + thatProduct.price), "totalValue");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return price == product.price &&
                Objects.equals(endDate, product.endDate) &&
                Objects.equals(startDate, product.startDate) &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, startDate, price, name, category);
    }
}
