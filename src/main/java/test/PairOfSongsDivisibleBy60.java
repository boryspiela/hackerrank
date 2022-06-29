package test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we
 * want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 */

class Result {

    private static final int MAX_LENGTH = 60;

    public static long playlist(List<Integer> songs) {
        final var counts = songs.stream()
                                .collect(Collectors.groupingBy(song -> song % MAX_LENGTH, Collectors.counting()));
        var count = 0;
        for (int mod : counts.keySet()) {
            final var first = Objects.requireNonNullElse(counts.replace(mod, 0L), 0L);
            final int reminder = MAX_LENGTH - mod;
            final var second = Objects.requireNonNullElse(counts.replace(reminder, 0L), 0L);
            count += (first + second) / 2;
        }
        return count;
    }
}

public class PairOfSongsDivisibleBy60 {

    public static void main(String[] args) {
        long result = Result.playlist(List.of(60, 4, 10, 50, 90, 30, 120));
        System.out.println(result);
    }
}
