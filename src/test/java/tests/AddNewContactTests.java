package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("dusm5@gmail.com").withPassword("Dudu12345@"));
        }
    }

    @Test(dataProvider = "contactSuccess",dataProviderClass = DataProviderContact.class)
    public void addContactTestAllFields(Contact contact) {
        int i = new Random().nextInt(1000) + 1000;
//
//        Contact contact = Contact.builder()
//                .name("Tony" + i)
//                .lastName("Molly")
//                .phone("343434343" + i)
//                .email("molly" + i + "@gmail.com")
//                .address("Haifa")
//                .description("all fields")
//                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" +i+".png");
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }

    @Test(dataProvider = "contactCSV",dataProviderClass = DataProviderContact.class)
    public void addContactTestAllFieldsCSV(Contact contact) {
        int i = new Random().nextInt(1000) + 1000;
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" +i+".png");
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
    //for Jenkins

    @Test(groups = {"smoke","regress","retest"})
    public void addContactTestReqFields() {
        int i = new Random().nextInt(1000) + 1000;

        Contact contact = Contact.builder()
                .name("TonyReq")
                .lastName("Molly")
                .phone("343434343" + i)
                .email("molly" + i + "@gmail.com")
                .address("Haifa")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addContactTestWrongName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Molly")
                .phone("343434343344")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("wrong name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addContactTestWrongLastName() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .phone("343434343344")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("wrong last name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }


    @Test(dataProvider = "contactWrongPhone",dataProviderClass = DataProviderContact.class)
    public void addContactTestWrongPhone(Contact contact) {
//        Contact contact = Contact.builder()
//                .name("Tony")
//                .lastName("Molly")
//                .phone("")
//                .email("molly@gmail.com")
//                .address("Haifa")
//                .description("wrong phone")
//                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addContactTestWrongEmail() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343434343344")
                .email("mollygmail.com")
                .address("Haifa")
                .description("wrong email")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid: "));


    }

    @Test
    public void addContactTestWrongAddress() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343434343344")
                .email("molly@gmail.com")
                .address("")
                .description("wrong address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }
}