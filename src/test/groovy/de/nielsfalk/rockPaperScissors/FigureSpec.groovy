package de.nielsfalk.rockPaperScissors

import spock.lang.Specification
import spock.lang.Unroll

import static de.nielsfalk.rockPaperScissors.Figure.*
import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.successful
import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.tie
import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.unsuccessful

/**
 * @author Niels Falk
 */
class FigureSpec extends Specification {

    @Unroll
    def "#first defending #second is #expectedResult"() {
        expect:
        first.defend(second) == expectedResult

        where:
        first    | second   | expectedResult
        rock     | scissors | successful
        paper    | rock     | successful
        scissors | paper    | successful
        rock     | rock     | tie
        paper    | paper    | tie
        scissors | scissors | tie
        rock     | paper    | unsuccessful
        paper    | scissors | unsuccessful
        scissors | rock     | unsuccessful
    }
}
