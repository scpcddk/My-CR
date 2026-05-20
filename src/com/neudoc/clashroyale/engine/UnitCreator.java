package com.neudoc.clashroyale.engine;

import com.neudoc.clashroyale.constant.Team;
import com.neudoc.clashroyale.model.GameEntity;

@FunctionalInterface
public interface UnitCreator {
    GameEntity create(Team team,double x,double y);
}
