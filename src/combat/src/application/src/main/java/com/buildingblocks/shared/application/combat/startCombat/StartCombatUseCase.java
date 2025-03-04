package com.buildingblocks.shared.application.combat.startCombat;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.combat.Combat;
import com.buildingblocks.shared.application.combat.domain.combat.values.ScenarioId;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import reactor.core.publisher.Mono;

import static com.buildingblocks.shared.application.shared.combat.CombatMapper.mapToResponse;

public class StartCombatUseCase implements ICommandUseCase<StartCombatRequest, Mono<CombatResponse>> {
    private final IEventsRepositoryPort repository;

    public StartCombatUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CombatResponse> execute(StartCombatRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    Combat combat = Combat.from(request.getAggregateId(), events);
                    combat.startCombat();
                    combat.setScenarioId(ScenarioId.of(request.getScenarioId()));
                    combat.getUncommittedEvents().forEach(repository::save);
                    combat.markEventAsCommited();

                    return mapToResponse(combat);
                });
    }

}
