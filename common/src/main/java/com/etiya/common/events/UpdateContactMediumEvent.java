package com.etiya.common.events;

public record UpdateContactMediumEvent(String contactMediumId,
                                       String customerId,
                                       String type,
                                       String value,
                                       boolean isPrimary) {
}
