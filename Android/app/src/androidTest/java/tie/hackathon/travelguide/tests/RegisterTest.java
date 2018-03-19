package tie.hackathon.travelguide.tests;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
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

import tie.hackathon.travelguide.Splash;
import tie.hackathon.travelguide.espressorobot.RegisterRobot;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterTest {

    private static final String USER1_NAME = "User";
    private static final String USER1_CELLPHONE = "981255226";
    private static final String USER1_PASS = "1234";

    private static final String ErrorToastMessage = "Invalid Password or number";

    RegisterRobot registerRobot;

    @Rule
    public ActivityTestRule<Splash> mActivityTestRule = new ActivityTestRule<>(Splash.class);

    @Before
    public void setUp() {
        registerRobot = new RegisterRobot();
    }

    /**
     * Check Not register with empty fields.
     * Test Case: 03.
     */
    @Test
    public void checkRegisterEmptyFailed() throws InterruptedException {
        sleep(2000);
        registerRobot.clickSignUpButton();
        registerRobot.closeAlertDialog();
        sleep(1000);
        registerRobot.checkErrorNotificationMessager(mActivityTestRule, ErrorToastMessage);
        sleep(500);
    }

    /**
     * Check Not register when name field is empty.
     * Test Case: 04.
     */
    @Test
    public void checkRegisterEmptyNameFieldFailed() throws InterruptedException {
        sleep(2000);
        registerRobot.enterPhoneNumber(USER1_CELLPHONE);
        registerRobot.enterPassWord(USER1_PASS);
        registerRobot.clickSignUpButton();
        registerRobot.closeAlertDialog();
        sleep(1000);
        registerRobot.checkErrorNotificationMessager(mActivityTestRule, ErrorToastMessage);
        sleep(500);
    }

    /**
     * Check Not register when Phone field is empty.
     * Test Case: 05.
     */
    @Test
    public void checkRegisterEmptyPhoneNumberFieldFailed() throws InterruptedException {
        sleep(2000);
        registerRobot.enterName(USER1_NAME);
        registerRobot.enterPassWord(USER1_PASS);
        registerRobot.clickSignUpButton();
        registerRobot.closeAlertDialog();
        sleep(2000);
        registerRobot.checkErrorNotificationMessager(mActivityTestRule, ErrorToastMessage);
        sleep(500);
    }

    /**
     * Check Not register when Password field is empty.
     * Test Case: 06.
     */
    @Test
    public void checkRegisterEmptyPasswordFieldFailed() throws InterruptedException {
        sleep(2000);
        registerRobot.enterName(USER1_NAME);
        registerRobot.enterPhoneNumber(USER1_CELLPHONE);
        registerRobot.clickSignUpButton();
        registerRobot.closeAlertDialog();
        sleep(2000);
        registerRobot.checkErrorNotificationMessager(mActivityTestRule, ErrorToastMessage);
        sleep(600);
    }

    private static Matcher<View> childAtPosition(
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
