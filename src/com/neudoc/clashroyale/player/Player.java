package com.neudoc.clashroyale.player;

import com.neudoc.clashroyale.constant.Team;
import com.neudoc.clashroyale.model.Deck;

public class Player {
    private final String playerId;
    private final Team team;
    private int gold = 1000;
    private int gems = 100;
    private final Deck currentDeck;

    public Player(String playerId,Team team,Deck deck) {
        this.playerId = playerId;
        this.team = team;
        this.currentDeck = deck;
    }

    public void showHand() {
        currentDeck.printDeckStatus(team == Team.BLUE ? "蓝色方" : "红色方");
    }

    public Deck getDeck() { return currentDeck; }
    public Team getTeam() { return team; }
}
