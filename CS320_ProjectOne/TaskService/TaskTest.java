import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task("TASK616", "Stop Collider", "Prevent collider event on Earth-616");

        assertEquals("TASK616", task.getTaskId());
        assertEquals("Stop Collider", task.getName());
        assertEquals("Prevent collider event on Earth-616", task.getDescription());
    }

    @Test
    void testTaskIdAcceptsTenCharacters() {
        Task task = new Task("SPIDER616X", "Patrol City", "Patrol Earth-616");
        assertEquals("SPIDER616X", task.getTaskId());
    }

    @Test
    void testTaskIdRejectsMoreThanTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Task("SPIDER616XX", "Patrol City", "Patrol Earth-616"));
    }

    @Test
    void testTaskIdRejectsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Task(null, "Portal Check", "Check portal on Earth-1610"));
    }

    @Test
    void testNameBoundaryAndValidation() {
        Task task = new Task("TASK2099", "ProtectTheSpiderCity",
                "Protect Nueva York on Earth-928");
        assertEquals("ProtectTheSpiderCity", task.getName());

        assertThrows(IllegalArgumentException.class,
                () -> new Task("TASK2099X", "ProtectTheSpiderCityX",
                        "Protect Nueva York on Earth-928"));
        assertThrows(IllegalArgumentException.class,
                () -> new Task("TASK2099Y", null, "Protect Nueva York on Earth-928"));
    }

    @Test
    void testDescriptionBoundaryAndValidation() {
        String fiftyCharacters = "Watch for anomalies across the Spider-Verse today!";
        String fiftyOneCharacters = fiftyCharacters + "X";

        Task task = new Task("TASK65", "Watch Portals", fiftyCharacters);
        assertEquals(fiftyCharacters, task.getDescription());

        assertThrows(IllegalArgumentException.class,
                () -> new Task("TASK65X", "Watch Portals", fiftyOneCharacters));
        assertThrows(IllegalArgumentException.class,
                () -> new Task("TASK65Y", "Watch Portals", null));
    }

    @Test
    void testEditableFieldsCanBeUpdated() {
        Task task = new Task("TASK138", "Band Practice", "Practice with the Spider-Band");

        task.setName("Save Multiverse");
        task.setDescription("Help the Spider-Society");

        assertEquals("Save Multiverse", task.getName());
        assertEquals("Help the Spider-Society", task.getDescription());
        assertEquals("TASK138", task.getTaskId());
    }

    @Test
    void testSettersRejectInvalidValues() {
        Task task = new Task("TASK1451", "Repair SPdr", "Repair the SP//dr suit");

        assertThrows(IllegalArgumentException.class,
                () -> task.setName("ThisTaskNameIsWayTooLong"));
        assertThrows(IllegalArgumentException.class,
                () -> task.setName(null));
        assertThrows(IllegalArgumentException.class,
                () -> task.setDescription("123456789012345678901234567890123456789012345678901"));
        assertThrows(IllegalArgumentException.class,
                () -> task.setDescription(null));
    }
}
