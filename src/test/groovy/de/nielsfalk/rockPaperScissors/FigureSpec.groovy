package de.nielsfalk.rockPaperScissors

import spock.lang.Specification

import static de.nielsfalk.rockPaperScissors.Figure.rock
import static de.nielsfalk.rockPaperScissors.Figure.scissors

/**
 * @author Niels Falk
 */
class FigureSpec extends Specification{
    def "rock should beat scissors"() {
        expect:
        rock > scissors
    }
}
