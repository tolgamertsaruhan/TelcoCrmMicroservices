package com.etiya.common.events;

public record DeletedAddressEvent(String addressId,
                                  String customerId) {
}
