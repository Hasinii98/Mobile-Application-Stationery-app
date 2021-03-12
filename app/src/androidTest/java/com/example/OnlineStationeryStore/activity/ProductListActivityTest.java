package com.example.OnlineStationeryStore.activity;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.OnlineStationeryStore.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class ProductListActivityTest {

        @Rule
        //creating an activity test rule to launch the productList activity
        public ActivityTestRule<ProductListActivity> eActivityTestRule = new ActivityTestRule<ProductListActivity>(ProductListActivity.class);

        private ProductListActivity eActivity = null;
        //creating a monitor to monitor the addProductActivity
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(ProductAddActivity.class.getName(),null,false);

        @Before
        public void setUp() throws Exception {
            eActivity = eActivityTestRule.getActivity();
        }

        @Test
        public void testLaunchOfProductEditActivityOnButtonClick(){
            assertNotNull(eActivity.findViewById(R.id.fabProduct));
            //on click of fabProduct second activity should be launched
            onView(withId(R.id.fabProduct)).perform(click());
            //when productActivity launches monitor will return the instances of the productAddActivity
            Activity ProductAddActivity =  getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
            assertNotNull(ProductAddActivity);
            ProductAddActivity.finish();
        }

        @After
        public void tearDown() throws Exception {
            eActivity = null;
        }
    }