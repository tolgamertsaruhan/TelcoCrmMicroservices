package com.etiya.common.events;

import java.util.Map;

public record CreateProductEvent (String productId, String billingAccountId,
        String productOfferId,
        String productName,
        Map<String, String> configurationValues){

}
