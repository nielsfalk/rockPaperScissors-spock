package de.nielsfalk.rockPaperScissors

import de.nielsfalk.rockPaperScissors.Player.Strategy
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static de.nielsfalk.rockPaperScissors.Game.TIE_RESULT_KEY
import static java.util.Optional.of

/**
 * @author Niels Falk
 */
class GameSpec extends Specification {
    @Shared
    def alwaysScissors = new Player("always scissors player", Strategy.ALWAYS_SCISSORS),
        another_always_scissors = new Player("another always scissors player", alwaysScissors.strategy),
        alwaysPaper =new Player("always paper player", Strategy.ALWAYS_PAPER);


    def "result message"() {
        given:
        def game = new Game(alwaysPaper, alwaysScissors)

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
        first          | second                  | expectedResult     | expectedFirstWinCount | expectedSecondWinCount | expectedTieCount
        alwaysPaper    | alwaysScissors          | of(alwaysScissors) | 0                     | 1                      | 0
        alwaysScissors | another_always_scissors | TIE_RESULT_KEY     | 0                     | 0                      | 1
        alwaysScissors | alwaysPaper             | of(alwaysScissors) | 1                     | 0                      | 0
    }
}


