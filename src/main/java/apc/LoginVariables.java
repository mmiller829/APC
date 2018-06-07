
package apc;

public class LoginVariables implements java.io.Serializable
{
    private final String username;
    private final String lanAddress;
    private final String wanAddress;
    private final String wanPort;
    
    public LoginVariables(String username, String lanAddress, String wanAddress, String wanPort)
    {
        this.username = username;
        this.lanAddress = lanAddress;
        this.wanAddress = wanAddress;
        this.wanPort = wanPort;
    }

    public String getUsername()
    {
        return username;
    }

    public String getLanAddress()
    {
        return lanAddress;
    }

    public String getWanAddress()
    {
        return wanAddress;
    }

    public String getWanPort()
    {
        return wanPort;
    }
}
