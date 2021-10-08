package at.ac.htlinn.androidexamples;

import java.util.ArrayList;
import java.util.List;

import at.ac.htlinn.androidexamples.simplecalculator.SimpleCalculatorActivity;

public class ActivityEntry {
    private String name;

    public ActivityEntry(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public Class getActivity() {
        return activity;
    }

    private Class activity;

    @Override
    public String toString() {
        return name;
    }

    /**
     * All Demos listed on the main page
     */
    public static List<ActivityEntry> all()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("...please select the demo...", null));
        all.add(new ActivityEntry("Simple Calculator", SimpleCalculatorActivity.class));
        return all;
    }

    /**
     * All demos listed on the Sensor page
     */
    public static List<ActivityEntry> allSensor()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("...please select the demo...", null));

        return all;
    }

    /**
     * All demos listed on the Sensor page
     */
    public static List<ActivityEntry> allConcurrent()
    {
        ArrayList<ActivityEntry> all = new ArrayList<ActivityEntry>();
        all.add(new ActivityEntry("...please select the demo...", null));
        return all;
    }
}
