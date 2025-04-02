package com.ecommerce.sportscenter.entity.OrderAggregate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingAddressTest {

    @Test
    void testShippingAddressSettersAndGetters() {
        ShippingAddress address = new ShippingAddress();
        address.setName("John Doe");
        address.setAddress1("123 Street");
        address.setAddress2("Apt 4B");
        address.setCity("Toronto");
        address.setState("ON");
        address.setZipcode("M5G 1Z1");
        address.setCountry("Canada");

        assertEquals("John Doe", address.getName());
        assertEquals("123 Street", address.getAddress1());
        assertEquals("Apt 4B", address.getAddress2());
        assertEquals("Toronto", address.getCity());
        assertEquals("ON", address.getState());
        assertEquals("M5G 1Z1", address.getZipcode());
        assertEquals("Canada", address.getCountry());
    }

    @Test
    void testShippingAddressBuilder() {
        ShippingAddress address = ShippingAddress.builder()
                .name("Jane Smith")
                .address1("456 Ave")
                .address2("Suite 2")
                .city("Vancouver")
                .state("BC")
                .zipcode("V6B 3K9")
                .country("Canada")
                .build();

        assertEquals("Jane Smith", address.getName());
        assertEquals("456 Ave", address.getAddress1());
        assertEquals("Suite 2", address.getAddress2());
        assertEquals("Vancouver", address.getCity());
        assertEquals("BC", address.getState());
        assertEquals("V6B 3K9", address.getZipcode());
        assertEquals("Canada", address.getCountry());
    }
}
