import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class RankingServer {

    List<Competitor> competitorList;

    public String execute(String command) throws Exception {
        competitorList = command.lines()
                .map(mapToComp)
                .sorted()
                .collect(Collectors.toList());
        return competitorList.toString();
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
