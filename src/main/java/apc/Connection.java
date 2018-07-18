package apc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection
{

    private final String ipAddress;
    private final int port;

    private final String TERMINALTYPE = "VT100";
    private TelnetClient telnetClient;
    private OutputStream outputStream;
    private PrintWriter printWriter;
    private InputStream inputStream;

    private final Logger logger = LogManager.getLogger(Connection.class);

    public Connection(String ipAddress, int port)
    {
        this.ipAddress = ipAddress;
        this.port = port;

    }

    public void connect() throws InvalidTelnetOptionException, IOException
    {
        telnetClient = new TelnetClient();
        TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler(TERMINALTYPE, false, false, true, false);
        EchoOptionHandler echoopt = new EchoOptionHandler(false, false, false, false);
        SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);

        telnetClient.addOptionHandler(ttopt);
        telnetClient.addOptionHandler(echoopt);
        telnetClient.addOptionHandler(gaopt);

        telnetClient.connect(ipAddress, port);

        outputStream = telnetClient.getOutputStream();
        printWriter = new PrintWriter(outputStream);

        inputStream = telnetClient.getInputStream();

        logger.info("Connected to " + ipAddress);

    }

    public void login(String username, String password) throws IOException, LoginFailException
    {
        logger.info("Logging in with " + username + " " + password);

        username += "\r";
        password += " -c\r";
        send(username);
        send(password);

        readLine();
        readLine();
        String string = readLine();

        if (!string.contains("Amer"))
        {
            logger.error(string);
            throw new LoginFailException();
        }

        while (!(string = readLine()).contains("APC>"))
        {
        }
        logger.info("Login is success");
    }

    public void send(String output) throws IOException
    {
        logger.debug("sending " + output);
        printWriter.print(output);
        printWriter.flush();
    }

    public String readLine() throws IOException
    {
        StringBuilder line = new StringBuilder();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) > 0)
        {

            String input = new String(buffer, 0, bytesRead);

            line.append(input);
            if (line.toString().contains("\r"))
            {
                break;
            }

        }
        return line.toString();
    }

    public String readResponse() throws IOException
    {
        StringBuilder line = new StringBuilder();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) > 0)
        {

            String input = new String(buffer, 0, bytesRead);

            line.append(input);
            if (line.toString().contains("APC>"))
            {
                break;
            }

        }
        return line.toString();
    }

    public void disconnect() throws IOException
    {
        send("logout\r");
        telnetClient.disconnect();
        logger.info("disconnected from " + ipAddress);
    }

}
