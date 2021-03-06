package org.openmrs.reference;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.reference.helper.TestPatient;
import org.openmrs.reference.page.*;
import org.openmrs.uitestframework.test.TestBase;
import org.junit.*;
import static org.junit.Assert.assertTrue;


/**
 * Created by nata on 24.07.15.
 */

public class NamePatientAccentedLetterTest extends TestBase {
    private HomePage homePage;
    private HeaderPage headerPage;
    private TestPatient patient;
    private RegistrationPage registrationPage;
    private ClinicianFacingPatientDashboardPage patientDashboardPage;

    @Before
    public void setUp() throws Exception {
        
        homePage = new HomePage(page);
        assertPage(homePage);
        headerPage = new HeaderPage(driver);
        registrationPage = new RegistrationPage(page);
        patientDashboardPage = new ClinicianFacingPatientDashboardPage(page);
        patient = new TestPatient();
    }

    @Test
    @Ignore
    public void namePatientAccentedLetterTest() throws Exception {
        homePage.goToRegisterPatientApp();
        patient.familyName = "Kłoczkowski";
        patient.givenName = "Mike";
        patient.gender = "Male";
        patient.estimatedYears = "25";
        patient.address1 = "5109 Jumillá";
        registrationPage.enterMegrePatient(patient);
        patientDashboardPage.waitForVisitLink();
        assertTrue(patientDashboardPage.visitLink().getText().contains("Created Patient Record: " + patient.givenName +" " + patient.familyName));
        patient.uuid =  patientDashboardPage.getPatientUuidFromUrl();
    }



    @After
    public void tearDown() throws Exception {
        headerPage.clickOnHomeIcon();
        deletePatient(patient.uuid);
        waitForPatientDeletion(patient.uuid);
        headerPage.logOut();
    }

}
