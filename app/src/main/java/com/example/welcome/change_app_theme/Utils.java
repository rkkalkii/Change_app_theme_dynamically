package com.example.welcome.change_app_theme;

/**
 * Created by welcome on 30-04-2017.
 */
import android.app.Activity;
import android.content.Intent;

public class Utils
{
    private static int sTheme;
    public final static int THEME_RED = 0;
    public final static int THEME_BLUE = 1;
    public final static int THEME_GREEN = 2;
    public final static int THEME_YELLOW = 3;
    public final static int THEME_VOILET = 4;
    public final static int THEME_PINK = 5;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_RED:
                activity.setTheme(R.style.RedTheme);
                break;
            case THEME_BLUE:
                activity.setTheme(R.style.BlueTheme);
                break;
            case THEME_GREEN:
                activity.setTheme(R.style.GreenTheme);
                break;
            case THEME_VOILET:
                activity.setTheme(R.style.VoiletTheme);
                break;
            case THEME_YELLOW:
                activity.setTheme(R.style.YellowTheme);
                break;
            case THEME_PINK:
                activity.setTheme(R.style.PinkTheme);
                break;
        }
    }

}
