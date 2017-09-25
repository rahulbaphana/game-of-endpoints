package com.tw.domain;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductTest {

    @Test
    public void product_with_same_price_and_name_should_be_same() throws ParseException {
        Product detolSoap = new Product(40, "Detol Soap");

        assertEquals(detolSoap, new Product(40, "Detol Soap"));
    }

    @Test
    public void product_within_date_range_inclusive_of_startDate_and_endDate_should_be_active() throws ParseException {
        String endDate = "2017-12-04";
        String startDate = "2017-01-30";
        Product detolSoap = new Product(endDate, startDate,40, "kitchen", "Detol Soap");

        assertTrue("Within date range should be active!", detolSoap.isActiveFor("2017-10-05"));
        assertTrue("Should be active on endDate!", detolSoap.isActiveFor(endDate));
        assertTrue("Should be active on startDate!",detolSoap.isActiveFor(startDate));
    }

    @Test
    public void product_outside_date_range_should_be_inactive() throws ParseException {
        Product detolSoap = new Product("2017-04-04", "2017-01-30",40, "kitchen", "Detol Soap");

        assertFalse(detolSoap.isActiveFor("2017-10-05"));
    }

    @Test
    public void product_without_end_date_should_be_active_after_start_date() throws ParseException {
        Product detolSoap = new Product(null, "2017-01-30",40, "kitchen", "Detol Soap");

        assertTrue(detolSoap.isActiveFor("2018-10-05"));
    }

}