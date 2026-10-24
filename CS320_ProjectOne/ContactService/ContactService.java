import java.util.HashMap;
import java.util.Map;

public class ContactService {
    // stores contacts in memory using their id as the key
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }

        // make sure an id is not already being used
        String contactId = contact.getContactId();
        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("A contact with this ID already exists.");
        }

        contacts.put(contactId, contact);
    }

    public void deleteContact(String contactId) {
        // only remove the contact if its id exists
    	if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID was not found.");
        }

        contacts.remove(contactId);
    }

    public void updateContact(String contactId, String firstName, String lastName,
                              String phone, String address) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID was not found.");
        }

        // validate every replacement value before changing the stored contact
        Contact validatedContact = new Contact(contactId, firstName, lastName, phone, address);

        contact.setFirstName(validatedContact.getFirstName());
        contact.setLastName(validatedContact.getLastName());
        contact.setPhone(validatedContact.getPhone());
        contact.setAddress(validatedContact.getAddress());
    }

    // mainly used to find a contact by its id
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}
