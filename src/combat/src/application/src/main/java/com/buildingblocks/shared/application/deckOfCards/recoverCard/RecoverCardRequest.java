package com.buildingblocks.shared.application.deckOfCards.recoverCard;

import com.buildingblocks.shared.application.Request;

public class RecoverCardRequest extends Request {
    private final String cardId;

    public RecoverCardRequest(String aggregateId, String cardId) {
        super(aggregateId);
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
