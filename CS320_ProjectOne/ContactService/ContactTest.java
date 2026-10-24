import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {

    @Test
    void testContactCreation() {
        Contact contact = new Contact("SP616", "Peter", "Parker", "5073816240", "Earth-616");

        assertEquals("SP616", contact.getContactId());
        assertEquals("Peter", contact.getFirstName());
        assertEquals("Parker", contact.getLastName());
        assertEquals("5073816240", contact.getPhone());
        assertEquals("Earth-616", contact.getAddress());
    }

    @Test
    void testContactIdAcceptsTenCharacters() {
        Contact contact = new Contact("MILES1610X", "Miles", "Morales", "6129047318", "Earth-1610");
        assertEquals("MILES1610X", contact.getContactId());
    }

    @Test
    void testContactIdRejectsMoreThanTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("PETER616XYZ", "Peter", "Parker", "2184679053", "Earth-616"));
    }

    @Test
    void testContactIdRejectsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Gwen", "Stacy", "6517324806", "Earth-65"));
    }

    @Test
    void testFirstNameBoundaryAndValidation() {
        Contact contact = new Contact("SP2099", "Miguel2099", "OHara", "3208461957", "Earth-928");
        assertEquals("Miguel2099", contact.getFirstName());

        assertThrows(IllegalArgumentException.class,
                () -> new Contact("SP2099X", "Miguel2099X", "OHara", "7635192840", "Earth-928"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("SP2099Y", null, "OHara", "9526741308", "Earth-928"));
    }

    @Test
    void testLastNameBoundaryAndValidation() {
        Contact contact = new Contact("HOBIE138", "Hobie", "SpiderPunk", "4148073621", "Earth-138");
        assertEquals("SpiderPunk", contact.getLastName());

        assertThrows(IllegalArgumentException.class,
                () -> new Contact("HOBIE138X", "Hobie", "SpiderPunkX", "6083519472", "Earth-138"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("HOBIE138Y", "Hobie", null, "9204867135", "Earth-138"));
    }

    @Test
    void testPhoneMustBeExactlyTenDigits() {
        new Contact("NOIR90214", "Peter", "Parker", "7152639048", "Earth-90214");

        assertThrows(IllegalArgumentException.class,
                () -> new Contact("NOIR9021", "Peter", "Parker", "715263904", "Earth-90214"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("NOIR9022", "Peter", "Parker", "71526390481", "Earth-90214"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("NOIR9023", "Peter", "Parker", "71526A9048", "Earth-90214"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("NOIR9024", "Peter", "Parker", null, "Earth-90214"));
    }

    @Test
    void testAddressBoundaryAndValidation() {
        String thirtyCharacters = "123456789012345678901234567890";
        String thirtyOneCharacters = thirtyCharacters + "X";

        Contact contact = new Contact("PENI14512", "Peni", "Parker", "2627318450", thirtyCharacters);
        assertEquals(thirtyCharacters, contact.getAddress());

        assertThrows(IllegalArgumentException.class,
                () -> new Contact("PENI1451", "Peni", "Parker", "5349182760", thirtyOneCharacters));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("PENI1452", "Peni", "Parker", "3196408275", null));
    }

    @Test
    void testEditableFieldsCanBeUpdated() {
        Contact contact = new Contact("SP616X", "Peter", "Parker", "5073816240", "Earth-616");

        contact.setFirstName("Miles");
        contact.setLastName("Morales");
        contact.setPhone("6129047318");
        contact.setAddress("Earth-1610");

        assertEquals("Miles", contact.getFirstName());
        assertEquals("Morales", contact.getLastName());
        assertEquals("6129047318", contact.getPhone());
        assertEquals("Earth-1610", contact.getAddress());
        assertEquals("SP616X", contact.getContactId());
    }

    @Test
    void testSettersRejectInvalidValues() {
        Contact contact = new Contact("GWEN65Z", "Gwen", "Stacy", "6517324806", "Earth-65");

        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("LastNameTooLong"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345"));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setAddress("1234567890123456789012345678901"));
    }
}
