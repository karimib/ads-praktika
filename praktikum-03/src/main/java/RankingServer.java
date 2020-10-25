import org.w3c.dom.stylesheets.LinkStyle;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


public class RankingServer implements CommandExecutor {

    List<Competitor> competitorList;
    List<Competitor> rankedList;
    List<Competitor> namedList;
    AtomicInteger count;

    /**
     * Produces a list sorted by rank (time) and a list sorted by name and birthyear.
     */

    public String execute(String command) {
        count = new AtomicInteger(1);

        competitorList = command.lines()
                .map(mapToComp)
                .collect(Collectors.toUnmodifiableList());

        rankedList = competitorList.stream()
                .sorted(Comparator.comparing(Competitor::getTime))
                .peek(competitor -> competitor.setRank(count.getAndIncrement()))
                .collect(Collectors.toUnmodifiableList());

        namedList = competitorList.stream()
                .sorted(Comparator.comparing(Competitor::getName).thenComparing(Competitor::getJg))
                .collect(Collectors.toUnmodifiableList());

        return namedList.toString();
    }

    /**
     * Maps a line string to a competitor object
     */

    private Function<String, Competitor> mapToComp = (line) -> {
        String[] input = line.split(String.valueOf(';'));
        Competitor competitor;
        try {
            competitor = new Competitor(
                    Integer.parseInt(input[0]),
                    input[1],
                    Integer.parseInt(input[2]),
                    input[3],
                    input[4]
            );
        } catch (ParseException e) {
            throw new RuntimeException("Parsing failed at: " + line);
        }
        return competitor;
    };

}
