package co.il.listview;

public class Mission
{
    private String missionName;
    private String description;
    private boolean done;

    public Mission(String missionName, String description, boolean done)
    {
        this.missionName = missionName;
        this.description = description;
        this.done = done;
    }

    public String getMissionName()
    {
        return missionName;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean isDone()
    {
        return done;
    }

    public void setMissionName(String missionName)
    {
        this.missionName = missionName;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }
}
