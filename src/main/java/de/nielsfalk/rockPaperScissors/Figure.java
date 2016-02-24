package de.nielsfalk.rockPaperScissors;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.*;
import static java.util.Collections.unmodifiableMap;

/**
 * @author Niels Falk
 */
public enum Figure {
    scissors, rock, paper;

    private static final Map<Figure, Figure> rules = unmodifiableMap(new HashMap<Figure, Figure>() {{
        put(scissors, paper);
        put(rock, scissors);
        put(paper, rock);
    }});
    private final static Random random = new Random();

    public DefendResult defend(Figure other) {
        if (rules.get(this) == other) {
            return successful;
        }
        return this == other ? tie : unsuccessful;
    }

    public static Figure random() {
        return values()[random.nextInt(values().length)];
    }

    public enum DefendResult {
        successful, tie, unsuccessful
    }
}
