package com.example.OnlineStationeryStore;

import com.example.OnlineStationeryStore.activity.ProductAddActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private ProductAddActivity productAddActivity;

    @Before
    public void setUp(){
        productAddActivity = new ProductAddActivity();
    }

    @Test
    public void total_isCorrect(){
        float result = productAddActivity.getTotalPrice(80,4);
        assertEquals(320, 80*4);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

}

