package contactservice;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact is null");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate contactId");
        }
        contacts.put(id, contact);
    }

    public void deleteContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("contactId not found");
        }
        contacts.remove(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getRequiredContact(contactId);
        contact.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact contact = getRequiredContact(contactId);
        contact.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact contact = getRequiredContact(contactId);
        contact.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact contact = getRequiredContact(contactId);
        contact.setAddress(address);
    }

    // helper for tests
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    private Contact getRequiredContact(String contactId) {
        if (contactId == null || !contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("contactId not found");
        }
        return contacts.get(contactId);
    }
}
