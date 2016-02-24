package de.nielsfalk.rockPaperScissors;

import java.util.function.Supplier;

import static de.nielsfalk.rockPaperScissors.Figure.*;

/**
 * @author Niels Falk
 */
class Player {
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
        @SuppressWarnings("unused")
        private static final Supplier<Figure> ALWAYS_SCISSORS = () -> scissors;
    }
}
