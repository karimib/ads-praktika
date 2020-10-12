import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Paths.*;
import static org.junit.jupiter.api.Assertions.*;

class RankingServerTest {

    RankingServer rankingServer;


    @BeforeEach
    void setUp() {
        rankingServer = new RankingServer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void execute() throws Exception {
        String command = prepareCommand();
        System.out.println(rankingServer.execute(command));
    }


    private String prepareCommand() throws IOException {
        Path resourceDirectory = get("src", "test", "resources", "RangZuerich.csv");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(absolutePath), "ISO-8859-1"));
        StringBuffer b = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            b.append(line);
            b.append('\n');
        }
        return b.toString();
    }
}