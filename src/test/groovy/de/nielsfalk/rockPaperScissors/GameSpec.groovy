package de.nielsfalk.rockPaperScissors

import spock.lang.Specification

import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_PAPER
import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_SCISSORS

/**
 * @author Niels Falk
 */
class GameSpec extends Specification{
    def "result message"() {
        def resultMessage = new Game(ALWAYS_PAPER, ALWAYS_SCISSORS).resultMessage

        expect:
        resultMessage.contains "0 rounds are tie"
        resultMessage.contains "always scissors player won 0 rounds"
        resultMessage.contains "always paper player won 0 rounds"
    }
}
