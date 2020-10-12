import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


public class RankingServer {

    List<Competitor> competitorList;
    List<Competitor> namedList;
    AtomicInteger count = new AtomicInteger(1);

    public String execute(String command) throws Exception {
        competitorList = command.lines()
                .map(mapToComp)
                .sorted(Comparator.comparing(Competitor::getTime))
                .peek(competitor -> competitor.setRank(count.getAndIncrement()))
                .collect(Collectors.toList());


        namedList = competitorList.stream()
                .sorted(Comparator.comparing(Competitor::getName).thenComparing(Competitor::getJg))
                .collect(Collectors.toList());

        return namedList.toString();
    }

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
            return null;
        }
        return competitor;
    };

}
