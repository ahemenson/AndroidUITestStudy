package tie.hackathon.travelguide.espressorobot;

import android.support.test.espresso.matcher.ViewMatchers;
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
import static org.hamcrest.core.IsNot.not;


/**
 * Created by ahemenson on 15/03/18.
 */

public class LoginRobot {

    public void gotoLoginPage() {
        sleep();
        onView(allOf(ViewMatchers.withId(R.id.login), withText("Log in"))).perform(click());
    }

    public void enterNum(String cellphoneNumber) {
        onView(allOf(withId(R.id.input_num_login))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(replaceText(cellphoneNumber), closeSoftKeyboard());
    }

    public void enterLogin(String pass) {
        onView(allOf(withId(R.id.input_pass_login))).perform(click());
        onView(allOf(withId(R.id.input_pass_login))).perform(replaceText(pass), closeSoftKeyboard());
    }

    public void clickLoginButton() {
        onView(allOf(withId(R.id.ok_login), withText("Log in"))).perform(click());
        pressBack();
        sleep();

    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void checkErrorNotificationMessager(ActivityTestRule<Splash> mActivityTestRule, String message) {
        onView(withText(message))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
}






