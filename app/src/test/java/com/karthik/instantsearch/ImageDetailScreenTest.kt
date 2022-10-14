package com.karthik.instantsearch

import android.R
import android.app.Activity
import android.view.View
import android.widget.Button
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowToast

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*


@RunWith(RobolectricTestRunner::class)
internal class ImageDetailScreenTest {

    private var activity: Activity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(ImageDetailScreen::class.java).create().resume().get()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun checkActivityNotNull() {
        assertNotNull(activity)
    }

    /*@Test
    @Throws(java.lang.Exception::class)
    fun testButtonClickShouldShowToast() {
        val view: Button = activity.findViewById(R.id.buttonSh) as Button
        assertNotNull(view)
        view.performClick()
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Lala"))
    }*/

    @Test
    @Throws(java.lang.Exception::class)
    fun buttonClickShouldStartNewActivity() {
        val button: Button = activity!!.findViewById(com.karthik.instantsearch.R.id.buttonShowToast)
        button.performClick()
        val intent = Shadows.shadowOf(activity).peekNextStartedActivity()
        assertEquals(
            NextActivity::class.java.getCanonicalName(),
            intent.component!!.className
        )
    }

}