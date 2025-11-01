package com.etiya.common.events;

public record DeleteContactMediumEvent(String contactMediumId,
                                       String customerId,
                                       String type,
                                       String value,
                                       boolean isPrimary,
                                       String deletedDate) {
}
