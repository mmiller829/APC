/** These variables are for storing in a file
 */

package apc;

import java.io.Serializable;

public class LoginProfile implements Serializable
{
    public  String username;
    public  String password;
    public  String lanAddress;
    public  String wanAddress;
    public  String wanPort;

    public LoginProfile(String userName, String password, String lanAddress, String wanAddress, String wanPort)
    {
        this.username = userName;
        this.password = password;
        this.lanAddress = lanAddress;
        this.wanAddress = wanAddress;
        this.wanPort = wanPort;
    }    

    public String getUsername()
    {
        return username;
    }

    public void setUserName(String userName)
    {
        this.username = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLanAddress()
    {
        return lanAddress;
    }

    public void setLanAddress(String lanAddress)
    {
        this.lanAddress = lanAddress;
    }

    public String getWanAddress()
    {
        return wanAddress;
    }

    public void setWanAddress(String wanAddress)
    {
        this.wanAddress = wanAddress;
    }

    public String getWanPort()
    {
        return wanPort;
    }

    public void setWanPort(String wanPort)
    {
        this.wanPort = wanPort;
    }
    
    @Override
    public String toString()
    {
        return username;
    }
}
