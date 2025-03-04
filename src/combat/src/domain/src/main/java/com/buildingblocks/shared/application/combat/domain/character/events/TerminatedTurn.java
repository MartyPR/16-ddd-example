package com.buildingblocks.shared.application.combat.domain.character.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class TerminatedTurn extends DomainEvent {
    private final String characterId;

    public TerminatedTurn( String characterId) {
        super(EventsEnum.TERMINATED_TURN.name());
        this.characterId = characterId;
    }

    public String getCharacterId() {
        return characterId;
    }
}
