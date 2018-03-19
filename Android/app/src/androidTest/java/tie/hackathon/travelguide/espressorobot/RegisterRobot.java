package tie.hackathon.travelguide.espressorobot;

import android.support.test.rule.ActivityTestRule;

import tie.hackathon.travelguide.R;
import tie.hackathon.travelguide.Splash;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by ahemenson on 16/03/18.
 */

public class RegisterRobot {

    public void enterName(String name) {
        onView(allOf(withId(R.id.input_name_signup))).perform(click());
        onView(allOf(withId(R.id.input_name_signup))).perform(replaceText(name), closeSoftKeyboard());
    }

    public void enterPhoneNumber(String phoneNumber) {
        onView(allOf(withId(R.id.input_num_signup))).perform(click());
        onView(allOf(withId(R.id.input_num_signup))).perform(replaceText(phoneNumber), closeSoftKeyboard());
    }

    public void enterPassWord(String password) {
        onView(allOf(withId(R.id.input_pass_signup))).perform(click());
        onView(allOf(withId(R.id.input_pass_signup))).perform(replaceText(password), closeSoftKeyboard());
    }

    public void clickSignUpButton() {
        onView(allOf(withId(R.id.ok_signup), withText("Signup"))).perform(click());
    }

    public void closeAlertDialog() {
        pressBack();
    }

    public void checkErrorNotificationMessager(ActivityTestRule<Splash> mActivityTestRule, String message) {
        onView(withText(message))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

}
