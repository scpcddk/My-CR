package com.neudoc.clashroyale.model;

import com.neudoc.clashroyale.constant.Team;
import com.neudoc.clashroyale.constant.EntityState;

public class Tower extends GameEntity{
    public Tower(String name, int hp, double range, Team team,
                 double x, double y, double attackSpeed, double attackPower) {
        super(name, hp, range, team, EntityState.IDLE, x, y, attackSpeed, attackPower);
    }
}
