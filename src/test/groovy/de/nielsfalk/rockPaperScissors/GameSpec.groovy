package de.nielsfalk.rockPaperScissors

import spock.lang.Specification

import static de.nielsfalk.rockPaperScissors.Game.TIE_RESULT_KEY
import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_PAPER
import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_SCISSORS
import static java.util.Optional.empty
import static java.util.Optional.of

/**
 * @author Niels Falk
 */
class GameSpec extends Specification {
    def "result message"() {
        given:
        def game = new Game(ALWAYS_PAPER, ALWAYS_SCISSORS)

        when:
        def resultMessage = game.resultMessage

        then:
        resultMessage.contains "0 rounds are tie"
        resultMessage.contains "always scissors player won 0 rounds"
        resultMessage.contains "always paper player won 0 rounds"
    }

    def "play round"() {
        given:
        def game = new Game(ALWAYS_PAPER, ALWAYS_SCISSORS)

        when:
        def winner = game.playRound()

        then:
        winner == of(ALWAYS_SCISSORS)
        game.result[of(ALWAYS_SCISSORS)] ==1
        game.result[of(ALWAYS_PAPER)] ==0
        game.result.get(Game.TIE_RESULT_KEY) ==0
    }
}
