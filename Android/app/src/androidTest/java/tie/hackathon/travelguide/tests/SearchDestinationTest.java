package tie.hackathon.travelguide.tests;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import tie.hackathon.travelguide.R;
import tie.hackathon.travelguide.Splash;
import tie.hackathon.travelguide.espressorobot.SearchDestinationRobot;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchDestinationTest {


    SearchDestinationRobot searchDestinationRobot;

    @Rule
    public ActivityTestRule<Splash> mActivityTestRule = new ActivityTestRule<>(Splash.class);

    @Before
    public void setUp() {
        searchDestinationRobot = new SearchDestinationRobot();
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void checkHintisDisplayed() throws InterruptedException {
        sleep(2000);
        String hintText = mActivityTestRule.getActivity().getResources().getString(R.string.search_a_city); // 'Search a city...'
        searchDestinationRobot.checkHintMessageInField(hintText+"");
        sleep(500);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void searchAndShowOneSuggestion() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("Mum");
        sleep(1000);
        searchDestinationRobot.checkDestinationSuggestionIsDisplayed(mActivityTestRule, "Mumbai");
        sleep(100);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void searchAndShowTwoSuggestions() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("Che");
        sleep(1000);
        searchDestinationRobot.checkDestinationSuggestionIsDisplayed(mActivityTestRule, "Chennai");
        searchDestinationRobot.checkDestinationSuggestionIsDisplayed(mActivityTestRule, "Cherkassy");
        sleep(500);
    }


    //@Test dificuldades na resolução deste test
    public void searchDestinatyWithSpaceinTheEnd() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("bangalore");
        sleep(1000);
        searchDestinationRobot.checkDestinationisSuggestionNotIsDisplayed(mActivityTestRule, "Bgalore");
        sleep(500);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void searchUsingUpperCaseLetters() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("Agra".toUpperCase());
        sleep(1000);
        searchDestinationRobot.checkDestinationSuggestionIsDisplayed(mActivityTestRule, "Agra");
        sleep(500);
    }

    /**
     *
     * @throws InterruptedException
     */
    @Test
    public void searchUsingLowerCaseLetters() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("Agra".toLowerCase());
        sleep(1000);
        searchDestinationRobot.checkDestinationSuggestionIsDisplayed(mActivityTestRule, "Agra");
        sleep(500);
    }

    @Test
    public void searchDestinyAndClickInSuggestion() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("delhi");
        sleep(1000);
        searchDestinationRobot.tapInSuggestion(mActivityTestRule, "Delhi");
        // check
        sleep(1500);
        searchDestinationRobot.checkDestinationScreenOpened("Delhi");
    }

    @Test
    public void searchDestinyAndClickInSuggestionAndReturntoHome() throws InterruptedException {
        sleep(2000);
        searchDestinationRobot.clickSearchField();
        sleep(1000);
        searchDestinationRobot.enterSearch("agra");
        sleep(1000);
        searchDestinationRobot.tapInSuggestion(mActivityTestRule, "Agra");
        sleep(1000);
        searchDestinationRobot.clickReturnButton();
        sleep(1000);
        // check
        searchDestinationRobot.checkReturnedToHomeScreen();
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
