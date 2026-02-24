package contactservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactTest {

    private Contact makeValidContact() {
        return new Contact("C1", "John", "Smith", "1234567890", "123 Main St");
    }

    // ---------- constructor success ----------

    @Test
    void testCreateContactSuccess() {
        Contact contact = makeValidContact();

        Assertions.assertEquals("C1", contact.getContactId());
        Assertions.assertEquals("John", contact.getFirstName());
        Assertions.assertEquals("Smith", contact.getLastName());
        Assertions.assertEquals("1234567890", contact.getPhone());
        Assertions.assertEquals("123 Main St", contact.getAddress());
    }

    // ---------- boundary acceptance ----------

    @Test
    void testMaxLengthsAccepted() {
        String id10 = "1234567890";
        String first10 = "ABCDEFGHIJ";   // 10
        String last10 = "KLMNOPQRST";    // 10
        String phone10 = "0123456789";   // 10 digits
        String addr30 = "123456789012345678901234567890"; // 30

        Contact contact = new Contact(id10, first10, last10, phone10, addr30);

        Assertions.assertEquals(id10, contact.getContactId());
        Assertions.assertEquals(first10, contact.getFirstName());
        Assertions.assertEquals(last10, contact.getLastName());
        Assertions.assertEquals(phone10, contact.getPhone());
        Assertions.assertEquals(addr30, contact.getAddress());
    }

    // ---------- constructor invalid ----------

    @Test
    void testContactIdNullThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "John", "Smith", "1234567890", "123 Main"));
    }

    @Test
    void testContactIdTooLongThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", "John", "Smith", "1234567890", "123 Main"));
    }

    @Test
    void testFirstNameNullThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", null, "Smith", "1234567890", "123 Main"));
    }

    @Test
    void testFirstNameTooLongThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "TooLongName1", "Smith", "1234567890", "123 Main"));
    }

    @Test
    void testLastNameNullThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", null, "1234567890", "123 Main"));
    }

    @Test
    void testLastNameTooLongThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "TooLongName1", "1234567890", "123 Main"));
    }

    @Test
    void testPhoneNullThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith", null, "123 Main"));
    }

    @Test
    void testPhoneNotTenDigitsThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith", "12345", "123 Main"));
    }

    @Test
    void testPhoneNotDigitsThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith", "12345ABCDE", "123 Main"));
    }

    @Test
    void testAddressNullThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith", "1234567890", null));
    }

    @Test
    void testAddressTooLongThrows() {
        String longAddress = "1234567890123456789012345678901";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Contact("C1", "John", "Smith", "1234567890", longAddress));
    }

    // ---------- setter success paths ----------

    @Test
    void testSettersUpdateFieldsSuccess() {
        Contact contact = makeValidContact();

        contact.setFirstName("Jane");
        contact.setLastName("Jones");
        contact.setPhone("9999999999");
        contact.setAddress("New Address");

        Assertions.assertEquals("Jane", contact.getFirstName());
        Assertions.assertEquals("Jones", contact.getLastName());
        Assertions.assertEquals("9999999999", contact.getPhone());
        Assertions.assertEquals("New Address", contact.getAddress());
    }

    // ----- setter boundary success (needed for coverage) -----

    @Test
    void testSetFirstNameMaxLengthAccepted() {
        Contact contact = makeValidContact();
        contact.setFirstName("ABCDEFGHIJ");
        Assertions.assertEquals("ABCDEFGHIJ", contact.getFirstName());
    }

    @Test
    void testSetLastNameMaxLengthAccepted() {
        Contact contact = makeValidContact();
        contact.setLastName("KLMNOPQRST");
        Assertions.assertEquals("KLMNOPQRST", contact.getLastName());
    }

    @Test
    void testSetPhoneTenDigitsAccepted() {
        Contact contact = makeValidContact();
        contact.setPhone("0123456789");
        Assertions.assertEquals("0123456789", contact.getPhone());
    }

    @Test
    void testSetAddressMaxLengthAccepted() {
        Contact contact = makeValidContact();
        String addr30 = "123456789012345678901234567890";
        contact.setAddress(addr30);
        Assertions.assertEquals(addr30, contact.getAddress());
    }

    // ---------- setter invalid ----------

    @Test
    void testSetFirstNameNullThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setFirstName(null));
    }

    @Test
    void testSetFirstNameTooLongThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setFirstName("TooLongName1"));
    }

    @Test
    void testSetLastNameNullThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setLastName(null));
    }

    @Test
    void testSetLastNameTooLongThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setLastName("TooLongName1"));
    }

    @Test
    void testSetPhoneNullThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setPhone(null));
    }

    @Test
    void testSetPhoneNotTenDigitsThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setPhone("12345"));
    }

    @Test
    void testSetPhoneNotDigitsThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setPhone("12345ABCDE"));
    }

    @Test
    void testSetAddressNullThrows() {
        Contact contact = makeValidContact();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setAddress(null));
    }

    @Test
    void testSetAddressTooLongThrows() {
        Contact contact = makeValidContact();
        String longAddress = "1234567890123456789012345678901";
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                contact.setAddress(longAddress));
    }

    // ---------- immutability proof ----------

    @Test
    void testContactIdNotUpdatable() {
        Contact contact = new Contact("IMMUTE", "John", "Smith", "1234567890", "123 Main");

        contact.setFirstName("Jane");
        contact.setLastName("Jones");
        contact.setPhone("9999999999");
        contact.setAddress("New Address");

        Assertions.assertEquals("IMMUTE", contact.getContactId());
    }
}
