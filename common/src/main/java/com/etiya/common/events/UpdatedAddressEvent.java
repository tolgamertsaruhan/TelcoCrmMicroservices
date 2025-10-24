package com.etiya.common.events;

public record UpdatedAddressEvent(String addressId,

                                  String customerId,
                                  String street,
                                  String houseNumber,
                                  String description,
                                  boolean isDefault) {
}
