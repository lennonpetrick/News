package com.test.news.presentation.articles;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.test.news.R;
import com.test.news.presentation.sources.SourcesActivity;
import com.test.news.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ArticlesActivityTest {

    @Rule
    public ActivityTestRule<SourcesActivity> mActivityRule
            = new ActivityTestRule<>(SourcesActivity.class);

    @Rule
    public ActivityTestRule<ArticlesActivity> mActivityRule2
            = new ActivityTestRule<>(ArticlesActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry
                .getInstance()
                .register(EspressoIdlingResource.get());
    }

    @After
    public void tearDown() {
        IdlingRegistry
                .getInstance()
                .unregister(EspressoIdlingResource.get());
    }

    @Test
    public void scrollThroughArticles() {
        onView(withId(R.id.rvSources))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, click()));

        onView(withId(R.id.rvArticles))
                .perform(RecyclerViewActions.scrollToPosition(7))
                .perform(RecyclerViewActions.scrollToPosition(17))
                .perform(RecyclerViewActions.scrollToPosition(27))
                .perform(RecyclerViewActions.scrollToPosition(37))
                .perform(RecyclerViewActions.scrollToPosition(47))
                .perform(RecyclerViewActions.scrollToPosition(57));
    }
}