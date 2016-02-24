package de.nielsfalk.rockPaperScissors

import spock.lang.Shared
import spock.lang.Specification

import static de.nielsfalk.rockPaperScissors.Figure.paper

/**
 * @author Niels Falk
 */
class PlayerSpec extends Specification {
    @Shared
    def alwaysPaper = new Player("always paper player", Player.Strategy.ALWAYS_PAPER)

    def "simple strategy"() {
        expect:
        alwaysPaper.chooseFigure() == paper
    }

    def "player has name"() {
        expect:
        alwaysPaper.name == "always paper player"
    }
}
