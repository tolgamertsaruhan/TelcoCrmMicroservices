package com.etiya.common.events;

public record DeleteContactMediumEvent(String contactMediumId,
                                       String customerId) {
}
