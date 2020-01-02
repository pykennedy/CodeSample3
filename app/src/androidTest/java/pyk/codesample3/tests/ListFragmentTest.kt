package pyk.codesample3.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pyk.codesample3.R
import pyk.codesample3.model.repo.MovieList
import pyk.codesample3.model.support.StaticValues
import pyk.codesample3.util.actions.ListItemActions
import pyk.codesample3.util.matchers.ToolbarMatcher
import pyk.codesample3.view.activity.MainActivity
import pyk.codesample3.view.adapter.MovieListAdapter

@RunWith(AndroidJUnit4::class) class ListFragmentTest {
    @get:Rule val activityRule: ActivityTestRule<MainActivity> =
            ActivityTestRule(MainActivity::class.java)

    @Before fun launch() {
        MovieList().clearChecks()
    }
    
    @After fun cleanup() {
        MovieList().getMovies().clear()
    }
    
    @Test fun titleIsCorrect() {
        assert(ToolbarMatcher.expectedTitle(activityRule.activity.supportActionBar,
                                            activityRule.activity.resources.getString(
                                                    R.string.navigation_label_list)))
    }
    
    @Test fun firstPagePopulated() {
        // first page is populated
        onView(withId(R.id.rv_list)).check(
                matches(hasDescendant(withText(StaticValues.movies1[0].title))))
    }
    
    @Test fun goToDetails() {
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(1, click()))
        // scuffed way of testing actionbar title change since the view matchers i was trying
        // weren't working. I left my attempt commented out so yall could see what i wanted to do
        assert(ToolbarMatcher.expectedTitle(activityRule.activity.supportActionBar,
                                            activityRule.activity.resources.getString(
                                                    R.string.navigation_label_details)))
    }
    
    @Test fun pullForSecondPage() {
        onView(withId(R.id.rv_list)).check(
                matches(not(hasDescendant(withText(StaticValues.movies2[0].title)))))
        scrollToBottom()
        swipeUpwards()
        // SUPER scuffed way of "waiting" for my infinite scroll to load since espresso
        // quits before submitList() can get called
        onView(withId(R.id.rv_list)).perform(click())
        pressBack()
        scrollToBottom()
        swipeUpwards()
        onView(withId(R.id.rv_list)).perform(scrollToPosition<MovieListAdapter.ViewHolder>(20))
        onView(withId(R.id.rv_list)).check(
                matches(hasDescendant(withText(StaticValues.movies2[3].title))))
    }
    
    @Test fun endOfPages() {
        onView(withId(R.id.rv_list)).check(
                matches(not(hasDescendant(withText(StaticValues.movies2[0].title)))))
        scrollToBottom()
        swipeUpwards()
        // SUPER scuffed way of "waiting" for my infinite scroll to load since espresso
        // quits before submitList() can get called
        onView(withId(R.id.rv_list)).perform(click())
        pressBack()
        scrollToBottom()
        swipeUpwards()
        // extra swipe as this test would frequently fail due to not making it to
        // the bottom before the last swipe
        swipeUpwards()
        
        onView(withText(R.string.no_more_movies)).inRoot(
                withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
    }
    
    @Test fun goToSpinnerInvalid() {
        // no movies selected
        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.spinner_rules)).inRoot(
                withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        
        // one movie selected
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(1,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.not_enough_movies)).inRoot(
                withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        
        // 7 movies selected
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(2,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(3,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(4,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(5,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(6,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(7,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.too_many_movies)).inRoot(
                withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
    }
    
    @Test fun goToSpinnerValid() {
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(1,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(2,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.rv_list)).perform(actionOnItemAtPosition<MovieListAdapter.ViewHolder>(3,
                                                                                        ListItemActions.clickChildViewWithId(
                                                                                                R.id.cb_selected)))
        onView(withId(R.id.fab)).perform(click())
    
        assert(ToolbarMatcher.expectedTitle(activityRule.activity.supportActionBar,
                                            activityRule.activity.resources.getString(
                                                    R.string.navigation_label_fatedecide)))
    }
    
    // because i spam these so much
    private fun scrollToBottom() {
        onView(withId(R.id.rv_list)).perform(scrollToPosition<MovieListAdapter.ViewHolder>(
                MovieList().getMovies().size-1))
    }
    
    // because i spam these so much
    private fun swipeUpwards() {
        onView(withId(R.id.rv_list)).perform(swipeUp())
    }
}