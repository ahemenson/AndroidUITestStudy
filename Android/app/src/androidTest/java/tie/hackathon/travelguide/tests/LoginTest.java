package tie.hackathon.travelguide.tests;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static java.lang.Thread.sleep;

import tie.hackathon.travelguide.Splash;
import tie.hackathon.travelguide.espressorobot.LoginRobot;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;



@RunWith(AndroidJUnit4.class)
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class LoginTest {

    private static final String USER1_CELLPHONE = "981255226";
    private static final String USER1_PASS = "1234";
    private static final String INVALID_USER1_CELLPHONE = "123";
    private static final String INVALID_USER1_PASS = "@";
    private static final String ErrorToastMessage = "Invalid Password or number";

    LoginRobot loginPage;

    @Rule
    public ActivityTestRule<Splash> mActivityTestRule = new ActivityTestRule<>(Splash.class);

    @Before
    public void setUp() {
        loginPage = new LoginRobot();
    }

    /**
     * Check unsuccessful Login using an invalid phone number
     * Test Case: 10.
     */
    @Test
    public void checkLoginWithInvalidPassFailed() throws InterruptedException {

        sleep(2000);
        loginPage.gotoLoginScreen();
        loginPage.enterPhoneNumber(USER1_CELLPHONE);
        loginPage.enterPassWord(INVALID_USER1_PASS);
        loginPage.clickLoginButton();
        loginPage.closeAlertDialog();
        sleep(1000);
        loginPage.checkErrorNotificationMessager(mActivityTestRule, ErrorToastMessage);
    }

    /**
     * Check unsuccessful Login using an invalid phone number
     * Test Case: 09.
     */
    @Test
    public void checkLoginWithInvalidCellphoneFailed() throws InterruptedException {
        sleep(2000);
        loginPage.gotoLoginScreen();
        loginPage.enterPhoneNumber(USER1_PASS);
        loginPage.enterPassWord(INVALID_USER1_CELLPHONE);
        loginPage.clickLoginButton();
        loginPage.closeAlertDialog();
        sleep(1000);
        loginPage.checkErrorNotificationMessager(mActivityTestRule, ErrorToastMessage);
    }

    /**
     * Login Sucessful using valid data (phone and password)
     * Test Case: 06
     */
    @Test
    public void loginSucessful() throws InterruptedException {
        sleep(2000);
        loginPage.gotoLoginScreen();
        loginPage.enterPhoneNumber(USER1_CELLPHONE);
        loginPage.enterPassWord(USER1_PASS);
        loginPage.clickLoginButton();
        loginPage.checkHomeScreen();
        sleep(1000);
    }



    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
