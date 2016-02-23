package de.nielsfalk.rockPaperScissors

import spock.lang.Specification

import java.util.function.Supplier

import static de.nielsfalk.rockPaperScissors.Figure.paper
import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_PAPER

/**
 * @author Niels Falk
 */
class PlayerSpec extends Specification {
    def "simple strategy"() {
        expect:
        ALWAYS_PAPER.chooseFigure == paper
    }

    def "player has name"() {
        ALWAYS_PAPER.name == "always paper player"
    }
}
