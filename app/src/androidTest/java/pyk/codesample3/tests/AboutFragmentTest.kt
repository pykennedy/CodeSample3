package pyk.codesample3.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pyk.codesample3.R
import pyk.codesample3.model.repo.MovieList
import pyk.codesample3.util.matchers.ToolbarMatcher
import pyk.codesample3.view.activity.MainActivity

@RunWith(AndroidJUnit4::class) class AboutFragmentTest {
    @get:Rule val activityRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)
    
    @Before fun launch() {
        MovieList().clearChecks()
        onView(withId(R.id.drawerLayout)).perform(DrawerActions.open())
        onView(withId(R.id.nv_drawer)).perform(NavigationViewActions.navigateTo(R.id.aboutFragment))
    }
    
    @After fun cleanup() {
        MovieList().getMovies().clear()
    }
    
    @Test fun titleIsCorrect() {
        assertTrue(ToolbarMatcher.expectedTitle(activityRule.activity.supportActionBar,
                                                activityRule.activity.resources.getString(
                                                        R.string.navigation_label_about)))
    }
    
    @Test fun dataIsCorrect() {
        onView(withId(R.id.tv_about)).check(
                matches(withText(activityRule.activity.resources.getString(R.string.about_string))))
    }
}