package com.etiya.common.events;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

public record CreateContactMediumEvent (
        String contactMediumId,
        String customerId,
        String type,
        String value,
        boolean isPrimary) {}
