import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Competitor implements Comparable<Competitor> {
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor(int startNr, String name, int jg, String country, String time) throws ParseException {
        this.name = name;
        this.country = country;
        this.time = parseTime(time);
        this.jg = jg;
        this.startNr = startNr;
        this.rank = rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(rank);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(Integer.toString(jg));
        sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    @Override
    public int compareTo(Competitor other) {
        if (this.time > other.time) {
            return 1;
        }
        if (this.time < other.time) {
            return -1;
        } else {
            return 0;
        }
    }
}
