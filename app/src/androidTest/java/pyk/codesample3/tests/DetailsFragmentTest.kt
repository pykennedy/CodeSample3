package pyk.codesample3.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
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
import pyk.codesample3.model.support.StaticValues
import pyk.codesample3.util.matchers.ToolbarMatcher
import pyk.codesample3.view.activity.MainActivity
import pyk.codesample3.view.adapter.MovieListAdapter

@RunWith(AndroidJUnit4::class) class DetailsFragmentTest {
    @get:Rule val activityRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)
    
    @Before fun launch() {
        MovieList().clearChecks()
        onView(withId(R.id.rv_list)).perform(
                actionOnItemAtPosition<MovieListAdapter.ViewHolder>(1, click()))
    }
    
    @After fun cleanup() {
        MovieList().getMovies().clear()
    }
    
    @Test fun titleIsCorrect() {
        assertTrue(ToolbarMatcher.expectedTitle(activityRule.activity.supportActionBar,
                                                activityRule.activity.resources.getString(
                                                        R.string.navigation_label_details)))
    }
    
    @Test fun dataIsCorrect() {
        onView(withId(R.id.tv_title)).check(matches(withText(StaticValues.movies1[1].title)))
        onView(withId(R.id.tv_release)).check(matches(withText(StaticValues.movies1[1].release)))
        onView(withId(R.id.tv_rating)).check(
                matches(withText(StaticValues.movies1[1].rating + " / 10")))
        onView(withId(R.id.tv_overview)).check(matches(withText(StaticValues.movies1[1].overview)))
    }
}