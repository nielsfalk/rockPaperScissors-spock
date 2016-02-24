package de.nielsfalk.rockPaperScissors;

import de.nielsfalk.rockPaperScissors.Figure.DefendResult;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

/**
 * @author Niels Falk
 */
public class Game {
    private static final Optional<Player> TIE_RESULT_KEY = empty();
    private final Map<Optional<Player>, Integer> result;
    private final Player first;
    private final Player second;

    public Game(Player first, Player second) {
        this.first = first;
        this.second = second;
        Stream<Optional<Player>> resultFile = Stream.of(of(first), of(second), TIE_RESULT_KEY);
        result = resultFile.collect(Collectors.toMap(optional -> optional, player1 -> 0));
    }

    public String getResultMessage() {
        return result.entrySet().stream().map(entry -> {
            if (entry.getKey().isPresent()) {
                return entry.getKey().get().getName() + " won " + entry.getValue() + " rounds";
            }
            return entry.getValue() + " rounds are tie";
        }).collect(joining("\n"));
    }

    public Optional<Player> playRound() {
        DefendResult defendResult = first.chooseFigure().defend(second.chooseFigure());
        Optional<Player> winner = getPlayer(defendResult);
        incrementResultFor(winner);
        return winner;
    }

    private void incrementResultFor(Optional<Player> winner) {
        result.put(winner, result.get(winner) + 1);
    }

    private Optional<Player> getPlayer(DefendResult firstPlayersDefendResult) {
        switch (firstPlayersDefendResult) {
            case successful:
                return of(first);
            case unsuccessful:
                return of(second);
            case tie:
            default:
                return TIE_RESULT_KEY;
        }
    }
}