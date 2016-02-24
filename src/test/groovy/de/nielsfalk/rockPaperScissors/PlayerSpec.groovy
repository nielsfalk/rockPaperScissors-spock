package de.nielsfalk.rockPaperScissors

import spock.lang.Shared
import spock.lang.Specification
import de.nielsfalk.rockPaperScissors.Player.Strategy

import static de.nielsfalk.rockPaperScissors.Figure.paper

/**
 * @author Niels Falk
 */
class PlayerSpec extends Specification {
    @Shared
    def alwaysPaper = new Player("always paper player", Strategy.ALWAYS_PAPER)

    def "player applies strategy"() {
        expect:
        alwaysPaper.chooseFigure() == paper
    }
}
