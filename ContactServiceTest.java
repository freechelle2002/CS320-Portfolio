package contactservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private Contact makeValidContact(String id) {
        return new Contact(id, "John", "Smith", "1234567890", "123 Main St");
    }

    private ContactService makeServiceWithOneContact(String id) {
        ContactService service = new ContactService();
        service.addContact(makeValidContact(id));
        return service;
    }

    @Test
    void testAddContactSuccess() {
        ContactService service = new ContactService();
        service.addContact(makeValidContact("C1"));
        Assertions.assertNotNull(service.getContact("C1"));
    }

    @Test
    void testAddContactNullThrows() {
        ContactService service = new ContactService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testAddDuplicateContactIdThrows() {
        ContactService service = new ContactService();
        service.addContact(makeValidContact("C1"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.addContact(makeValidContact("C1")));
    }

    @Test
    void testDeleteContactSuccess() {
        ContactService service = makeServiceWithOneContact("C1");
        service.deleteContact("C1");
        Assertions.assertNull(service.getContact("C1"));
    }

    @Test
    void testDeleteNullIdThrows() {
        ContactService service = new ContactService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteContact(null));
    }

    @Test
    void testDeleteMissingIdThrows() {
        ContactService service = new ContactService();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteContact("MISSING"));
    }

    @Test
    void testUpdateFirstNameSuccess() {
        ContactService service = makeServiceWithOneContact("C1");
        service.updateFirstName("C1", "Jane");
        Assertions.assertEquals("Jane", service.getContact("C1").getFirstName());
    }

    @Test
    void testUpdateLastNameSuccess() {
        ContactService service = makeServiceWithOneContact("C1");
        service.updateLastName("C1", "Jones");
        Assertions.assertEquals("Jones", service.getContact("C1").getLastName());
    }

    @Test
    void testUpdatePhoneSuccess() {
        ContactService service = makeServiceWithOneContact("C1");
        service.updatePhone("C1", "9999999999");
        Assertions.assertEquals("9999999999", service.getContact("C1").getPhone());
    }

    @Test
    void testUpdateAddressSuccess() {
        ContactService service = makeServiceWithOneContact("C1");
        service.updateAddress("C1", "New Address");
        Assertions.assertEquals("New Address", service.getContact("C1").getAddress());
    }

    @Test
    void testUpdateMissingContactThrows() {
        ContactService service = new ContactService();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.updateFirstName("MISSING", "Jane"));
    }
}
