package tie.hackathon.travelguide.espressorobot;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import tie.hackathon.travelguide.R;
import tie.hackathon.travelguide.Splash;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by ahemenson on 19/03/18.
 */

public class SearchDestinationRobot {

    public void clickSearchField() {
        onView(ViewMatchers.withId(R.id.cityname)).perform(click(), clearText());
    }

    public void enterSearch(String destiny) {
        onView(withId(R.id.cityname)).perform(typeText(destiny));
    }

    public void checkDestinationDisplayed(ActivityTestRule<Splash> mActivityTestRule, String destiny_Suggested) {
        onView(withText(destiny_Suggested))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    public void checkDestinationisNotDisplayed(ActivityTestRule<Splash> mActivityTestRule, String destiny_Suggested) {
        onView(withId(R.id.cityname))
                .inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(withText(not(containsString(destiny_Suggested)))));

    }

    public void checkHintMessageInField(String hintText) {
        onView(withId(R.id.cityname)).check(matches(withHint(hintText)));
    }

    /**
     *
     * @param expectedHint
     * @return
     */
    public static Matcher<View> withHint(final String expectedHint) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }

                String hint = ((EditText) view).getHint().toString();

                return expectedHint.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
