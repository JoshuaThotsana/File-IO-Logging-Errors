import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    Visitor alice = new Visitor("alice cooper",50,"2020/01/17","09:45",
            "general enquiry","Mesuli");

    @Test
    void save() {
        assertEquals("File already exists.",alice.save());
    }

    @Test
    void load() {
    }

}