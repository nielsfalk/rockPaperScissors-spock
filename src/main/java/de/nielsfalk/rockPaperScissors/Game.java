package de.nielsfalk.rockPaperScissors;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.of;
import static java.util.stream.Collectors.joining;

/**
 * @author Niels Falk
 */
public class Game {
    private final Map<Optional<Player>, Integer> result;

    public Game(Player first, Player second) {
        Stream<Optional<Player>> resultFile = Stream.of(of(first), of(second), Optional.<Player>empty());
        result = resultFile.collect(Collectors.toMap(optional -> optional, player1 -> 0));
    }

    public String getResultMessage() {
        return result.entrySet().stream().map(entry -> {
            if (entry.getKey().isPresent()){
                return entry.getKey().get().getName() + " won " + entry.getValue() + " rounds";
            }
            return entry.getValue() +" rounds are tie";
        }).collect(joining("\n"));
    }
}