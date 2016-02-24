package de.nielsfalk.rockPaperScissors

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static de.nielsfalk.rockPaperScissors.Game.TIE_RESULT_KEY
import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_PAPER
import static de.nielsfalk.rockPaperScissors.Player.ALWAYS_SCISSORS
import static java.util.Optional.of

/**
 * @author Niels Falk
 */
class GameSpec extends Specification {
    @Shared
    def another_always_scissors = new Player("always scissors player", ALWAYS_SCISSORS.strategy)

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

    @Unroll
    def "#expectedResult wins round (#first against #second) "() {
        given:
        def game = new Game(first, second)

        when:
        def winner = game.playRound()

        then:
        winner == expectedResult
        game.result[of(first)] == expectedFirstWinCount
        game.result[of(second)] == expectedSecondWinCount
        game.result.get(TIE_RESULT_KEY) == expectedTieCount

        where:
        first           | second                  | expectedResult      | expectedFirstWinCount | expectedSecondWinCount | expectedTieCount
        ALWAYS_PAPER    | ALWAYS_SCISSORS         | of(ALWAYS_SCISSORS) | 0                     | 1                      | 0
        ALWAYS_SCISSORS | another_always_scissors | TIE_RESULT_KEY      | 0                     | 0                      | 1
        ALWAYS_SCISSORS | ALWAYS_PAPER            | of(ALWAYS_SCISSORS) | 1                     | 0                      | 0
    }
}


