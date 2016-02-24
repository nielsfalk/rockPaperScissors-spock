package de.nielsfalk.rockPaperScissors

import spock.lang.Specification

import static de.nielsfalk.rockPaperScissors.Figure.paper

/**
 * @author Niels Falk
 */
class PlayerSpec extends Specification {
    def "player applies strategy"() {
        expect:
        Player.withConstantStrategy(paper).chooseFigure() == paper
    }
}
