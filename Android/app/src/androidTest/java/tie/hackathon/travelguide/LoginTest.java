package tie.hackathon.travelguide;


import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.File;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.estimote.sdk.EstimoteSDK.getApplicationContext;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.core.IsNot.not;



@RunWith(AndroidJUnit4.class)
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class LoginTest {

    private static final String USER1_CELLPHONE = "981255226";
    private static final String USER1_PASS = "1234";
    private static final String INVALID_USER1_CELLPHONE = "123";
    private static final String INVALID_USER1_PASS = "@";
    private Context mcontext = getInstrumentation().getContext();


    @Rule
    public ActivityTestRule<Splash> mActivityTestRule = new ActivityTestRule<>(Splash.class);


    @Before
    public void setUp() {

    }




    /**
     * XXX XXX XXX
     * Test Case: XX.
     */
    @Test
    public void checkLoginWithInvalidPass() {
        sleep();
        onView(allOf(withId(R.id.login), withText("Log in"))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(replaceText(USER1_CELLPHONE), closeSoftKeyboard());
        onView(allOf(withId(R.id.input_pass_login))).perform(click());
        onView(allOf(withId(R.id.input_pass_login))).perform(replaceText(INVALID_USER1_PASS), closeSoftKeyboard());
        onView(allOf(withId(R.id.ok_login), withText("Log in"))).perform(click());
        pressBack();
        sleep();
        onView(withText("Invalid Password or number"))
               .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
               .check(matches(isDisplayed()));

    }

    /**
     * XXX XXX XXX
     * Test Case: XX.
     */
    @Test
    public void checkLoginWithInvalidCellphone() {
        sleep();
        onView(allOf(withId(R.id.login), withText("Log in"))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(replaceText(INVALID_USER1_CELLPHONE), closeSoftKeyboard());
        onView(allOf(withId(R.id.input_pass_login))).perform(click());
        onView(allOf(withId(R.id.input_pass_login))).perform(replaceText(USER1_PASS), closeSoftKeyboard());
        onView(allOf(withId(R.id.ok_login), withText("Log in"))).perform(click());
        pressBack();
        sleep();
        onView(withText("Invalid Password or number"))
                .inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginSucessful() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        sleep();
        onView(allOf(withId(R.id.login), withText("Log in"))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(click());
        onView(allOf(withId(R.id.input_num_login))).perform(replaceText(USER1_CELLPHONE), closeSoftKeyboard());
        onView(allOf(withId(R.id.input_pass_login))).perform(click());
        onView(allOf(withId(R.id.input_pass_login))).perform(replaceText(USER1_PASS), closeSoftKeyboard());
        onView(allOf(withId(R.id.ok_login), withText("Log in"))).perform(click());
        onView(allOf(withText("Travel Mate"))).check(matches(withText("Travel Mate")));
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clearPreferences() {
        try {
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear YOUR_APP_PACKAGE_GOES HERE");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
