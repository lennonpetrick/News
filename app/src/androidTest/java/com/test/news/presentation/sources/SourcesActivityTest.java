package com.test.news.presentation.sources;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.test.news.R;
import com.test.news.presentation.articles.ArticlesActivity;
import com.test.news.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SourcesActivityTest {

    @Rule
    public IntentsTestRule<SourcesActivity> mActivityRule
            = new IntentsTestRule<>(SourcesActivity.class);

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
    public void openArticleActivity() {
        onView(withId(R.id.rvSources))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(4, click()));

        intended(hasComponent(ArticlesActivity.class.getName()));
    }

}