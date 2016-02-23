package de.nielsfalk.rockPaperScissors

import spock.lang.Specification
import spock.lang.Unroll

import static de.nielsfalk.rockPaperScissors.Figure.rock
import static de.nielsfalk.rockPaperScissors.Figure.scissors

/**
 * @author Niels Falk
 */
class FigureSpec extends Specification {

    @Unroll
    def "#first beats #second"() {
        expect:
        first > second

        where:
        first | second
        rock  | scissors
    }
}
