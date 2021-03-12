package com.example.OnlineStationeryStore.activity;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.OnlineStationeryStore.R;

import junit.extensions.ActiveTestSuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

//activity launch testing
public class ProductAddActivityTest {

    @Rule
    public ActivityTestRule<ProductAddActivity> pActivityTestRule = new ActivityTestRule(ProductAddActivity.class);
    //creating a reference to the activity
    private ProductAddActivity pActivity = null;

    @Before
    public void setUp() throws Exception {

        pActivity = pActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = pActivity.findViewById(R.id.textView9);
        //if view is not null activity launch is successful
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        pActivity = null;
    }
}