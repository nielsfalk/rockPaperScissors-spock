package de.nielsfalk.rockPaperScissors;

import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.successful;

/**
 * @author Niels Falk
 */
public enum Figure {
    scissors, rock, paper;

    public DefendResult defend(Figure other) {
        return successful;
    }

    public enum DefendResult {
        successful
    }
}
