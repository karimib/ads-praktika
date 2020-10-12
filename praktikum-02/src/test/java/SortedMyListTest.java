import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedMyListTest {

    SortedMyList list;

    @BeforeEach
    void setUp() {
        list = new SortedMyList<>();
    }



    @Test
    void addTest() {
        for(int i = 20; i > 0; i--) {
            list.add(i);
        }

    }


    @AfterEach
    void tearDown() {
    }
}