package com.etiya.common.events;

public record CreateAddressEvent(String addressId,
                                 String customerId,
                                 String districtName,
                                 String cityName,
                                 String street,
                                 String houseNumber,
                                 String description,
                                 boolean isDefault ){}

