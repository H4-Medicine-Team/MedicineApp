package com.ds.nofication.Models.Backend;

import static org.junit.jupiter.api.Assertions.*;

import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.ReminderListCaller;
import com.google.gson.Gson;

import org.junit.jupiter.api.Test;

public class ReminderListCallerTest {

    @Test
    public void onResponseTest(){
        ReminderListCaller caller = new ReminderListCaller(new Callbackable() {
            @Override
            public void updateCallback(Object update) {

            }

            @Override
            public void errorCallback(String errorMessage) {

            }
        });

        String mockData = "{\"patient\":{\"person\":{\"name\":\"Gurli\",\"surname\":\"Gris\",\"lastname\":\"Grisen\",\"identifier\":\"1111111111\"},\"address\":{\"zipcode\":2630,\"city\":\"Taastrup\",\"streetName\":\"Kingosvej 1\",\"country\":\"Denmark\"}},\"drugMedication\":[{\"dosage\":[{\"amount\":2,\"amountType\":1,\"interval\":{\"start\":\"2022-01-03T09:41:21.377412+01:00\",\"end\":\"2022-01-05T09:41:21.3774164+01:00\",\"consumptionTime\":\"2022-01-03T09:51:21.3774172+01:00\",\"days\":[1,2,3,4,5,6,0]}}],\"drug\":{\"name\":\"Minulet\",\"identifier\":\"1232321231\"},\"beginEndDate\":{\"startDate\":\"2022-01-03T09:41:21.3774188+01:00\",\"endDate\":\"2022-01-05T09:41:21.3774193+01:00\"},\"identifier\":\"123123123123\"}],\"organisation\":{\"name\":\"Apotek\",\"telephoneNumber\":\"4352525252\",\"emailAddress\":\"Apotek@mail.dk\",\"identifier\":\"123451231\"}}";

        assertDoesNotThrow(() -> caller.onResponse(mockData));
    }
}
