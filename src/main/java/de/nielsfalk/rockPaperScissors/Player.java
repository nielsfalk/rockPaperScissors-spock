package de.nielsfalk.rockPaperScissors;

import java.util.function.Supplier;

/**
 * @author Niels Falk
 */
class Player {
    private final Supplier<Figure> strategy;
    private final String name;

    Player(String name, Supplier<Figure> strategy) {
        this.strategy = strategy;
        this.name = name;
    }

    public static Player withConstantStrategy(Figure figure) {
        return new Player("always " + figure + " player", () -> figure);
    }

    Figure chooseFigure() {
        return strategy.get();
    }


    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
