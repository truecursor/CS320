import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        // appointment id is required and cannot be longer than 10 characters
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("invalid appointment id");
        }

        // appointment date is required and cannot be in the past
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("invalid appointment date");
        }

        // description is required and cannot be longer than 50 characters
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("invalid description");
        }

        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setAppointmentDate(Date appointmentDate) {
        // appointment date cannot be null or changed to a date in the past
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("invalid appointment date");
        }
        this.appointmentDate = appointmentDate;
    }

    public void setDescription(String description) {
        // description cannot be null or longer than 50 characters
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("invalid description");
        }
        this.description = description;
    }
}
