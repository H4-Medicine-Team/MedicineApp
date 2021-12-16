package com.ds.nofication.Models.Backend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class DrugTest {

    @Test
    void getName() {
        Drug drug = new Drug("abc", "123");
        assertEquals(drug.getName(), "abc");
    }

    @Test
    void getIdentifier() {
        Drug drug = new Drug("abc", "123");
        assertEquals(drug.getIdentifier(), "123");
    }
}