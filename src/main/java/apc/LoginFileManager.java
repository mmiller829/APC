
package apc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Stores a collection of login credentials.
 */
public class LoginFileManager implements java.io.Serializable
{
    private Map<String, LoginVariables> logins;
    
    public LoginFileManager()
    {
        logins = new LinkedHashMap<>(); //LinkedHashMap preserves insertion order
    }
    
    public void add(LoginVariables login)
    {
        logins.put(login.getUsername(), login);
    }
    
    public void add(String username, String lanAddress, String wanAddress, String wanPort)
    {
        logins.put(username, new LoginVariables(username, lanAddress, wanAddress, wanPort));
    }

    public void remove(String username)
    {
        logins.remove(username);
    }
    
    public LoginVariables get(String username)
    {
        return logins.get(username);
    }
    
    public Set<String> getUsernames()
    {
        return logins.keySet();
    }
    
    public Set<Map.Entry<String, LoginVariables>> getEntries()
    {
    	return logins.entrySet();
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
