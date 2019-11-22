package csci310.myapplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class signUpPageUITest {
    private String username;
    private String firstName;
    private String emailAdress;
    private String password;
    private String confirmPassword;
    @Rule
    public ActivityTestRule<Registration> activityRule =
            new ActivityTestRule<>(Registration.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        username = "beiyouzh@usc.edu";
        firstName = "beiyou";
        emailAdress = "beiyouzh@usc.edu";
        password = "abcd1234";
        confirmPassword = "abcd1234";
        SaveSharedPreference.clearUser(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.EditUsername))
                .perform(typeText(username), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.EditName))
                .perform(typeText(firstName), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.EditEmail))
                .perform(typeText(emailAdress), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.EditPassword))
                .perform(typeText(password), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.EditConfirmPassword)).perform(click())
                .perform(typeText(confirmPassword),ViewActions.closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());

        // Check that the text was changed.
        //onView(withText(R.string.t)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void CheckLogin() {

    }
}
