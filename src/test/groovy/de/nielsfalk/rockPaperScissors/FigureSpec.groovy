package de.nielsfalk.rockPaperScissors

import spock.lang.Specification
import spock.lang.Unroll

import static de.nielsfalk.rockPaperScissors.Figure.*
import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.successful

/**
 * @author Niels Falk
 */
class FigureSpec extends Specification {

    @Unroll
    def "#first beats #second"() {
        expect:
        first.defend(second) == expectedResult

        where:
        first    | second   | expectedResult
        rock     | scissors | successful
        paper    | rock     | successful
        scissors | paper    | successful
    }
}
