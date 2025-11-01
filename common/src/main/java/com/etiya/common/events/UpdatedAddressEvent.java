package com.etiya.common.events;

public record UpdatedAddressEvent(String addressId,

                                  String customerId,
                                  String street,
                                  String houseNumber,
                                  String description,
                                  String districtName,
                                  String cityName,

                                  boolean isDefault) {
}
