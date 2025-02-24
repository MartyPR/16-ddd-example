package com.buildingblocks.shared.application.combat.removeEnemy;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.combat.Combat;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import reactor.core.publisher.Mono;

import static com.buildingblocks.shared.application.shared.combat.CombatMapper.mapToResponse;

public class removeEnemyUseCase implements ICommandUseCase<removeEnemyRequest, Mono<CombatResponse>> {
    private final IEventsRepository repository;

    public removeEnemyUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CombatResponse> execute(removeEnemyRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Combat combat = Combat.from(request.getAggregateId(), events);
                    combat.removeEnemy(request.getEnemyId());
                    combat.getUncommittedEvents().forEach(repository::save);
                    combat.markEventAsCommited();

                    return mapToResponse(combat);
                });
    }
}
