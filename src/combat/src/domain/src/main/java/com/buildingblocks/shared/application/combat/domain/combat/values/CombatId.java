package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

public class CombatId extends Identity {
    public CombatId() {
        super();
    }

    private CombatId(String value) {
        super(value);
    }

    public static CombatId of(String value) {
        return new CombatId(value);
    }
}
