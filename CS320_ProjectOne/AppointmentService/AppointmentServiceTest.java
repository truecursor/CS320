import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

	private Appointment createAppointment(String appointmentId, String description) {
	    // create a valid appointment for each service test
	    long oneDay = 24 * 60 * 60 * 1000L;
	    Date date = new Date(System.currentTimeMillis() + oneDay);

	    return new Appointment(appointmentId, date, description);
	}

    @Test
    public void testAddAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = createAppointment("HAIR200", "haircut appointment");

        service.addAppointment(appointment);

        assertEquals(appointment, service.getAppointment("HAIR200"));
    }

    @Test
    public void testAddAppointmentRejectsNull() {
        AppointmentService service = new AppointmentService();

        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }

    @Test
    public void testAddDuplicateAppointmentId() {
        AppointmentService service = new AppointmentService();
        Appointment firstAppointment = createAppointment("EYE201", "eye exam");
        Appointment secondAppointment = createAppointment("EYE201", "eye exam");
        service.addAppointment(firstAppointment);

        assertThrows(IllegalArgumentException.class,
                () -> service.addAppointment(secondAppointment));
    }

    @Test
    public void testDeleteAppointment() {
        AppointmentService service = new AppointmentService();
        Appointment appointment = createAppointment("CAR202", "car maintenance");
        service.addAppointment(appointment);

        service.deleteAppointment("CAR202");

        assertNull(service.getAppointment("CAR202"));
    }

    @Test
    public void testDeleteMissingAppointment() {
        AppointmentService service = new AppointmentService();

        assertThrows(IllegalArgumentException.class,
                () -> service.deleteAppointment("DOC203"));
    }
}
