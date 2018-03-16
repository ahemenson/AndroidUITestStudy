package tie.hackathon.travelguide.espressorobot;


import android.support.test.espresso.ViewInteraction;

import tie.hackathon.travelguide.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static tie.hackathon.travelguide.tests.SignOutTest.childAtPosition;

/**
 * Created by eminho on 15/03/18.
 */

public class SignOutRobot {

    public void clickMenu() {
        onView(allOf(withContentDescription("Travel Guide"))).perform(click());
    }

    public  void clickLogoutOptionOnMenu() {
        ViewInteraction navigationMenuItemView = onView(allOf(childAtPosition(
                allOf(withId(R.id.design_navigation_view),
                        childAtPosition(
                                withId(R.id.nav_view),
                                0)),
                8),
                isDisplayed()));
        navigationMenuItemView.perform(click());
    }

    public void checkLoginScreen() {
        onView(allOf(withText("LoginActivity"))).check(matches(withText("LoginActivity")));
    }

    public void checkHomeScreen() {
        onView(allOf(withText("Travel Mate"))).check(matches(withText("Travel Mate")));
    }



}
