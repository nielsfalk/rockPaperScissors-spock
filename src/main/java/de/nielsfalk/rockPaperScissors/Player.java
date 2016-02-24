package de.nielsfalk.rockPaperScissors;

import java.util.function.Supplier;

import static de.nielsfalk.rockPaperScissors.Figure.paper;
import static de.nielsfalk.rockPaperScissors.Figure.scissors;

/**
 * @author Niels Falk
 */
public class Player {
    static final Player DEPRECATEDALWAYS_PAPER = new Player("always paper player", Strategy.ALWAYS_PAPER);

    private static Player alwaysPaper(String name, Supplier<Figure> alp) {
        return new Player(name, alp);
    }

    private final Supplier<Figure> strategy;
    private final String name;

    public Player(String name, Supplier<Figure> strategy) {
        this.strategy = strategy;
        this.name = name;
    }

    public Figure chooseFigure() {
        return strategy.get();
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    static final class Strategy {
        static final Supplier<Figure> ALWAYS_PAPER = () -> paper;
        static final Supplier<Figure> RANDOM = Figure::random;
        private static final Supplier<Figure> ALWAYS_SCISSORS = () -> scissors;
    }
}
