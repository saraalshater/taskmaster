//package com.example.taskmaster;
//
//import static androidx.test.espresso.Espresso.closeSoftKeyboard;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//import static java.util.regex.Pattern.matches;
//
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class MainActivityTest {
//
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityRule =
//            new ActivityScenarioRule<>(MainActivity.class);
//
//    public static final String username = "Sara";
//    public static final String taskName = "Name";
//    public static final String taskBody = "Body";
//    public static final String taskState = "Status";
//
//    @Test
//    public void testSettingButton() {
//        onView(withId(R.id.settings)).perform(click());
//        onView(withId(R.id.userName2)).perform(typeText(username));
//        onView(withId(R.id.saveData)).perform(click());
//        closeSoftKeyboard();
//        onView(isRoot()).perform(ViewActions.pressBack());
//
//        onView(withId(R.id.welcomeMsg)).check(matches(withText( username + "’s tasks")));
//    }
//    //////////////////////
//    @Test
//    public void testAddTaskButton() {
//        onView(withId(R.id.addTask)).perform(click());
//        onView(withId(R.id.titleid)).perform(typeText(taskName));
//        onView(withId(R.id.descriptionid)).perform(typeText(taskBody));
//        closeSoftKeyboard();
//        onView(withId(R.id.statusid)).perform(typeText(taskState));
//        closeSoftKeyboard();
//        onView(withId(R.id.saveData)).perform(click());
//    }
//
////    @Test
////    public void testGoToDetails() {
////        onView(withId(R.id.taskRecyclerView)).check(matches(isDisplayed()));
////        onView(withId(R.id.viewId)).check(matches(isDisplayed())).perform(click());
////
////    }
//}
