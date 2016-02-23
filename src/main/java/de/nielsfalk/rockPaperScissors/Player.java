package de.nielsfalk.rockPaperScissors;

import java.util.function.Supplier;

import static de.nielsfalk.rockPaperScissors.Figure.paper;
import static de.nielsfalk.rockPaperScissors.Figure.scissors;

/**
 * @author Niels Falk
 */
public class Player {
    private static final Player ALWAYS_PAPER = new Player("always paper player", () -> paper);
    private static final Player ALWAYS_SCISSORS = new Player("always scissors player", () -> scissors);
    private final Supplier<Figure> strategy;
    private final String name;

    public Player(String name, Supplier<Figure> strategy) {
        this.strategy = strategy;
        this.name = name;
    }

    public Figure getChooseFigure() {
        return strategy.get();
    }


    public String getName() {
        return name;
    }
}
