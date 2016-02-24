package de.nielsfalk.rockPaperScissors;

import de.nielsfalk.rockPaperScissors.Figure.DefendResult;
import de.nielsfalk.rockPaperScissors.Player.Strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.nielsfalk.rockPaperScissors.Figure.DefendResult.*;
import static java.lang.System.out;
import static java.util.Collections.unmodifiableMap;
import static java.util.Optional.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

/**
 * @author Niels Falk
 */
class Game {
    private final Map<Optional<Player>, Integer> result;
    private final Map<DefendResult, Optional<Player>> defendResultPlayerMap;
    private final Player first;
    private final Player second;

    private Game(Player first, Player second) {
        defendResultPlayerMap = unmodifiableMap(new HashMap<DefendResult, Optional<Player>>() {
            {
                put(successful, of(first));
                put(unsuccessful, of(second));
                put(tie, empty());
            }
        });
        this.first = first;
        this.second = second;
        result = defendResultPlayerMap.values().stream().collect(Collectors.toMap(optional -> optional, optional -> 0));
    }

    private String getResultMessage() {
        return result.entrySet().stream().map(entry -> {
            if (entry.getKey().isPresent()) {
                return entry.getKey().get().getName() + " won " + entry.getValue() + " rounds";
            }
            return entry.getValue() + " rounds are tie";
        }).collect(joining("\n"));
    }

    private Optional<Player> playRound() {
        DefendResult defendResult = first.chooseFigure().defend(second.chooseFigure());
        Optional<Player> winner = getWinner(defendResult);
        incrementResultFor(winner);
        return winner;
    }

    private void incrementResultFor(Optional<Player> winner) {
        result.put(winner, result.get(winner) + 1);
    }

    private Optional<Player> getWinner(DefendResult firstPlayersDefendResult) {
        return defendResultPlayerMap.get(firstPlayersDefendResult);
    }

    public static void main(String[] args) {
        Game game = new Game(
                new Player("always paper player", Strategy.ALWAYS_PAPER),
                new Player("random player", Strategy.RANDOM));
        range(0, 100).forEach(i -> game.playRound());
        out.println(game.getResultMessage());
    }
}