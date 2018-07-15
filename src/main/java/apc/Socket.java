package apc;

public class Socket
{

    private String name;
    private String number;
    private String state;

    public Socket(String number, String state, String name)
    {
        this.number = number;
        this.state = state;
        this.name = name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

}
