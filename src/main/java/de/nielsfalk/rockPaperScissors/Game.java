package de.nielsfalk.rockPaperScissors;

import de.nielsfalk.rockPaperScissors.Figure.DefendResult;
import de.nielsfalk.rockPaperScissors.Player.Strategy;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.Optional.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

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
        Optional<Player>[] resultKeys = new Optional[]{of(first), of(second), TIE_RESULT_KEY};
        result = Stream.of(resultKeys).collect(Collectors.toMap(optional -> optional, optional -> 0));
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

    public static void main(String[] args) {
        Game game = new Game(
                new Player("always paper player", Strategy.ALWAYS_PAPER),
                new Player("random player", Strategy.RANDOM));
        range(0, 100).forEach(i -> game.playRound());
        out.println(game.getResultMessage());
    }
}