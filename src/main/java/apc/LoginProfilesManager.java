package apc;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores a collection of login credentials.
 */
public class LoginProfilesManager implements java.io.Serializable
{

    private Map<String, LoginProfile> logins;
    private LoginProfile startupProfile;

    public LoginProfilesManager()
    {
        logins = new LinkedHashMap<>(); //LinkedHashMap preserves insertion order
    }

    public void add(LoginProfile login)
    {
        if (contains(login.getUsername()))
        {
            logins.replace(login.getUsername(), login);

            if (startupProfile.getUsername().equals(login.getUsername()))
            {
                setStartup(login);
            }
        }
        else
        {
            logins.put(login.getUsername(), login);
        }
    }

    public void add(String username, String password, String lanAddress, String wanAddress, String wanPort)
    {
        if (contains(username))
        {
            LoginProfile profile = new LoginProfile(username, password, lanAddress, wanAddress, wanPort);
            logins.replace(username, profile);

            if (startupProfile.getUsername().equals(username))
            {
                setStartup(profile);
            }
        }
        else
        {
            logins.put(username, new LoginProfile(username, password, lanAddress, wanAddress, wanPort));
        }
    }

    public void setStartup(LoginProfile profile)
    {
        this.startupProfile = profile;
    }

    public void remove(String username)
    {
        logins.remove(username);

        if (startupProfile.getUsername().equals(username))
        {
            if (logins.isEmpty())
            {
                startupProfile = null;
            }
            else
            {
                startupProfile = (LoginProfile) logins.values().toArray()[0];
            }
        }
    }

    public LoginProfile getStartup()
    {
        return startupProfile;
    }

    public LoginProfile get(String username)
    {
        return logins.get(username);
    }

    public Set<String> getUsernames()
    {
        return logins.keySet();
    }

    public Collection<LoginProfile> getEntries()
    {
        return logins.values();
    }

    public void clear()
    {
        logins.clear();
    }

    public boolean isEmpty()
    {
        return logins.isEmpty();
    }

    public int size()
    {
        return logins.size();
    }

    public boolean contains(String username)
    {
        return logins.containsKey(username);
    }
}
