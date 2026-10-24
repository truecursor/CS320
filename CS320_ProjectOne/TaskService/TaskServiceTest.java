import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {
    private TaskService service;
    private Task task;

    @BeforeEach
    void setUp() {
        service = new TaskService();
        task = new Task("TASK616", "Stop Collider", "Prevent collider event on Earth-616");
    }

    @Test
    void testAddTaskWithUniqueId() {
        service.addTask(task);
        assertSame(task, service.getTask("TASK616"));
    }

    @Test
    void testAddTaskRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
    }

    @Test
    void testAddTaskRejectsDuplicateId() {
        service.addTask(task);
        Task duplicateId = new Task("TASK616", "Portal Check", "Check portal on Earth-1610");

        assertThrows(IllegalArgumentException.class, () -> service.addTask(duplicateId));
    }

    @Test
    void testDeleteTaskById() {
        service.addTask(task);
        service.deleteTask("TASK616");

        assertNull(service.getTask("TASK616"));
    }

    @Test
    void testDeleteMissingTaskId() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask("MISSING01"));
    }

    @Test
    void testUpdateTaskFieldsById() {
        service.addTask(task);

        service.updateTask("TASK616", "Portal Check", "Check portal activity on Earth-1610");
        Task updated = service.getTask("TASK616");

        assertEquals("TASK616", updated.getTaskId());
        assertEquals("Portal Check", updated.getName());
        assertEquals("Check portal activity on Earth-1610", updated.getDescription());
    }

    @Test
    void testUpdateMissingTaskId() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateTask("UNKNOWN01", "Watch Portals", "Monitor Earth-65"));
    }

    @Test
    void testInvalidUpdateDoesNotPartiallyChangeTask() {
        service.addTask(task);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateTask("TASK616", "Portal Check",
                        "123456789012345678901234567890123456789012345678901"));

        Task unchanged = service.getTask("TASK616");
        assertEquals("Stop Collider", unchanged.getName());
        assertEquals("Prevent collider event on Earth-616", unchanged.getDescription());
    }
}
