package de.nielsfalk.rockPaperScissors;

import java.util.function.Supplier;

import static de.nielsfalk.rockPaperScissors.Figure.paper;

/**
 * @author Niels Falk
 */
public class Player {
    private static final Player ALWAYS_PAPER = new Player("always paper player", () -> paper);
    private final Supplier<Figure> strategy;
    private final String name;

    public Player(String name, Supplier<Figure> strategy) {
        this.strategy = strategy;
        this.name = name;
    }

    public Figure getChooseFigure() {
        return strategy.get();
    }
}
