import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

	private Date futureDate() {
	    // keep the test date safely in the future
	    long oneDay = 24 * 60 * 60 * 1000L;
	    return new Date(System.currentTimeMillis() + oneDay);
	}

    @Test
    public void testAppointmentCreation() {
        Date date = futureDate();
        Appointment appointment = new Appointment("HAIR100", date, "haircut appointment");

        assertEquals("HAIR100", appointment.getAppointmentId());
        assertEquals(date, appointment.getAppointmentDate());
        assertEquals("haircut appointment", appointment.getDescription());
    }

    @Test
    public void testAppointmentIdTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("APPOINTMENT1", futureDate(), "eye exam"));
    }

    @Test
    public void testAppointmentIdNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment(null, futureDate(), "car maintenance"));
    }

    @Test
    public void testAppointmentDateInPast() {
        long oneDay = 24 * 60 * 60 * 1000L;
        Date pastDate = new Date(System.currentTimeMillis() - oneDay);

        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("CAR101", pastDate, "car maintenance"));
    }

    @Test
    public void testAppointmentDateNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("EYE102", null, "eye exam"));
    }

    @Test
    public void testDescriptionTooLong() {
        String longDescription = "this description is intentionally longer than fifty characters total";

        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("DOC103", futureDate(), longDescription));
    }

    @Test
    public void testDescriptionNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("HAIR104", futureDate(), null));
    }

    @Test
    public void testUpdateAppointmentDate() {
        Appointment appointment = new Appointment("CAR105", futureDate(), "car maintenance");
        long twoDays = 2 * 24 * 60 * 60 * 1000L;
        Date newDate = new Date(System.currentTimeMillis() + twoDays);

        appointment.setAppointmentDate(newDate);

        assertEquals(newDate, appointment.getAppointmentDate());
    }

    @Test
    public void testUpdateDescription() {
        Appointment appointment = new Appointment("EYE106", futureDate(), "eye exam");

        appointment.setDescription("annual eye exam");

        assertEquals("annual eye exam", appointment.getDescription());
    }

    @Test
    public void testAppointmentIdAcceptsTenCharacters() {
        Appointment appointment = new Appointment("APPT123456", futureDate(), "eye exam");

        assertEquals("APPT123456", appointment.getAppointmentId());
    }

    @Test
    public void testDescriptionAcceptsFiftyCharacters() {
        String fiftyCharacters = "12345678901234567890123456789012345678901234567890";
        Appointment appointment = new Appointment("DOC107", futureDate(), fiftyCharacters);

        assertEquals(fiftyCharacters, appointment.getDescription());
    }

    @Test
    public void testUpdateAppointmentDateRejectsNull() {
        Date originalDate = futureDate();
        Appointment appointment = new Appointment("CAR108", originalDate, "car maintenance");

        assertThrows(IllegalArgumentException.class, () -> appointment.setAppointmentDate(null));
        assertEquals(originalDate, appointment.getAppointmentDate());
    }

    @Test
    public void testUpdateAppointmentDateRejectsPastDate() {
        Date originalDate = futureDate();
        Appointment appointment = new Appointment("CAR109", originalDate, "car maintenance");
        long oneDay = 24 * 60 * 60 * 1000L;
        Date pastDate = new Date(System.currentTimeMillis() - oneDay);

        assertThrows(IllegalArgumentException.class, () -> appointment.setAppointmentDate(pastDate));
        assertEquals(originalDate, appointment.getAppointmentDate());
    }

    @Test
    public void testUpdateDescriptionRejectsNull() {
        Appointment appointment = new Appointment("EYE110", futureDate(), "eye exam");

        assertThrows(IllegalArgumentException.class, () -> appointment.setDescription(null));
        assertEquals("eye exam", appointment.getDescription());
    }

    @Test
    public void testUpdateDescriptionRejectsMoreThanFiftyCharacters() {
        Appointment appointment = new Appointment("EYE111", futureDate(), "eye exam");
        String fiftyOneCharacters = "123456789012345678901234567890123456789012345678901";

        assertThrows(IllegalArgumentException.class,
                () -> appointment.setDescription(fiftyOneCharacters));
        assertEquals("eye exam", appointment.getDescription());
    }

    @Test
    public void testUpdateDescriptionAcceptsFiftyCharacters() {
        Appointment appointment = new Appointment("EYE112", futureDate(), "eye exam");
        String fiftyCharacters = "12345678901234567890123456789012345678901234567890";

        appointment.setDescription(fiftyCharacters);

        assertEquals(fiftyCharacters, appointment.getDescription());
        assertEquals("EYE112", appointment.getAppointmentId());
    }
}
