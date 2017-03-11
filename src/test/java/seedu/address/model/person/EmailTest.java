package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.model.Task.StartTime;

public class EmailTest {

    @Test
    public void isValidEmail() {
        // blank email
        assertFalse(StartTime.isValidTime("")); // empty string
        assertFalse(StartTime.isValidTime(" ")); // spaces only

        // missing parts
        assertFalse(StartTime.isValidTime("@webmail.com")); // missing local part
        assertFalse(StartTime.isValidTime("peterjackwebmail.com")); // missing '@' symbol
        assertFalse(StartTime.isValidTime("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(StartTime.isValidTime("-@webmail.com")); // invalid local part
        assertFalse(StartTime.isValidTime("peterjack@-")); // invalid domain name
        assertFalse(StartTime.isValidTime("peter jack@webmail.com")); // spaces in local part
        assertFalse(StartTime.isValidTime("peterjack@web mail.com")); // spaces in domain name
        assertFalse(StartTime.isValidTime("peterjack@@webmail.com")); // double '@' symbol
        assertFalse(StartTime.isValidTime("peter@jack@webmail.com")); // '@' symbol in local part
        assertFalse(StartTime.isValidTime("peterjack@webmail@com")); // '@' symbol in domain name

        // valid email
        assertTrue(StartTime.isValidTime("PeterJack_1190@WEB.Mail.com"));
        assertTrue(StartTime.isValidTime("a@b"));  // minimal
        assertTrue(StartTime.isValidTime("test@localhost"));   // alphabets only
        assertTrue(StartTime.isValidTime("123@145"));  // numeric local part and domain name
        assertTrue(StartTime.isValidTime("a1@sg50.org"));  // mixture of alphanumeric and dot characters
        assertTrue(StartTime.isValidTime("_user_@_do_main_.com_"));    // underscores
        assertTrue(StartTime.isValidTime("peter_jack@a_very_long_domain_AVLD.com"));   // long domain name
        assertTrue(StartTime.isValidTime("if.you.dream.it_you.can.do.it@youth_email.com"));    // long local part
    }
}
