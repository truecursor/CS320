import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        // appointment must exist before it can be added
        if (appointment == null) {
            throw new IllegalArgumentException("appointment cannot be null");
        }

        // duplicate appointment ids are not allowed
        if (appointments.containsKey(appointment.getAppointmentId())) {
            throw new IllegalArgumentException("appointment id already exists");
        }

        appointments.put(appointment.getAppointmentId(), appointment);
    }

    public void deleteAppointment(String appointmentId) {
        // remove the appointment that matches the supplied id
        if (appointments.remove(appointmentId) == null) {
            throw new IllegalArgumentException("appointment id not found");
        }
    }

    public Appointment getAppointment(String appointmentId) {
        // this is used to verify stored appointments in the unit tests
        return appointments.get(appointmentId);
    }
}
