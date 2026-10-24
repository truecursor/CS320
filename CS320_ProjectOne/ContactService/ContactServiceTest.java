import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {
    private ContactService service;
    private Contact peter;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        peter = new Contact("SP616", "Peter", "Parker", "5073816240", "Earth-616");
    }

    @Test
    void testAddContactWithUniqueId() {
        service.addContact(peter);
        assertSame(peter, service.getContact("SP616"));
    }

    @Test
    void testAddContactRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testAddContactRejectsDuplicateId() {
        service.addContact(peter);
        Contact duplicateId = new Contact("SP616", "Miles", "Morales", "6129047318", "Earth-1610");

        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicateId));
    }

    @Test
    void testDeleteContactById() {
        service.addContact(peter);
        service.deleteContact("SP616");

        assertNull(service.getContact("SP616"));
    }

    @Test
    void testDeleteMissingContactId() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("MISSING01"));
    }

    @Test
    void testUpdateContactFieldsById() {
        service.addContact(peter);

        service.updateContact("SP616", "Miguel", "OHara", "3208461957", "Earth-928");
        Contact updated = service.getContact("SP616");

        assertEquals("SP616", updated.getContactId());
        assertEquals("Miguel", updated.getFirstName());
        assertEquals("OHara", updated.getLastName());
        assertEquals("3208461957", updated.getPhone());
        assertEquals("Earth-928", updated.getAddress());
    }

    @Test
    void testUpdateMissingContactId() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateContact("UNKNOWN01", "Gwen", "Stacy", "6517324806", "Earth-65"));
    }

    @Test
    void testInvalidUpdateDoesNotPartiallyChangeContact() {
        service.addContact(peter);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateContact("SP616", "Miles", "Morales", "123", "Earth-1610"));

        Contact unchanged = service.getContact("SP616");
        assertEquals("Peter", unchanged.getFirstName());
        assertEquals("Parker", unchanged.getLastName());
        assertEquals("5073816240", unchanged.getPhone());
        assertEquals("Earth-616", unchanged.getAddress());
    }
}
