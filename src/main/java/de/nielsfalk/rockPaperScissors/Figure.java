package de.nielsfalk.rockPaperScissors;

import java.util.HashMap;
import java.util.Map;

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

    public DefendResult defend(Figure other) {
        if (rules.get(this) == other) {
            return successful;
        }
        return this == other ? tie : unsuccessful;
    }

    public enum DefendResult {
        successful, tie, unsuccessful
    }
}
