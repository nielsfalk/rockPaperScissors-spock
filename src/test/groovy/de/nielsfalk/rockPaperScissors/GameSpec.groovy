package de.nielsfalk.rockPaperScissors

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static de.nielsfalk.rockPaperScissors.Figure.paper
import static de.nielsfalk.rockPaperScissors.Figure.scissors
import static java.util.Optional.of
import static java.util.Optional.ofNullable

/**
 * @author Niels Falk
 */
class GameSpec extends Specification {
    @Shared
    Player alwaysScissors = Player.withConstantStrategy(scissors),
        anotherAlwaysScissors = Player.withConstantStrategy(scissors),
        alwaysPaper = Player.withConstantStrategy(paper);


    def "result message contain a line for tie and each player"() {
        given:
        def game = new Game(alwaysPaper, alwaysScissors)

        when:
        def resultMessage = game.resultMessage.split "\n"

        then:
        resultMessage.size() == 3
        resultMessage.contains "0 rounds are tie"
        resultMessage.contains "$alwaysScissors won 0 rounds"
        resultMessage.contains "$alwaysPaper won 0 rounds"
    }

    def "result message has correct singular"() {
        given:
        game.playRound()

        when:
        def resultMessage = game.resultMessage.split "\n"


        then:
        resultMessage.contains expectedSingular

        where:
        game                                            | expectedSingular
        new Game(alwaysPaper, alwaysScissors)           | "$alwaysScissors won 1 round"
        new Game(alwaysScissors, anotherAlwaysScissors) | "1 round is tie"
    }

    @Unroll
    def "#expectedWinner wins round (#first against #second) "() {
        given:
        def game = new Game(first, second)

        when:
        def winner = game.playRound()

        then:
        winner == ofNullable(expectedWinner)
        game.result[of(first)] == expectedFirstWinCount
        game.result[of(second)] == expectedSecondWinCount
        game.result.get(Optional.empty()) == expectedTieCount

        where:
        first          | second                | expectedWinner | expectedFirstWinCount | expectedSecondWinCount | expectedTieCount
        alwaysPaper    | alwaysScissors        | alwaysScissors | 0                     | 1                      | 0
        alwaysScissors | anotherAlwaysScissors | null           | 0                     | 0                      | 1
        alwaysScissors | alwaysPaper           | alwaysScissors | 1                     | 0                      | 0
    }
}


