package com.buildingblocks.shared.application.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Scope implements IValueObject {
    private final Integer value;

    private Scope(Integer value) {
        this.value = value;
    }

    public static Scope of(Integer value) {
        return new Scope(value);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }
}
