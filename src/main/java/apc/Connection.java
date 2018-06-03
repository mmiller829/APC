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

    private String ipAddress;
    private int port;

    private final String TERMINALTYPE = "VT100";
    private TelnetClient telnetClient;
    private ConnectionReceiveThread connectionReceiveThread;
    private OutputStream outputStream;
    private PrintWriter printWriter;

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

        connectionReceiveThread = new ConnectionReceiveThread(this);
        connectionReceiveThread.start();

        outputStream = telnetClient.getOutputStream();
        printWriter = new PrintWriter(outputStream);

        logger.info("Connected to " + ipAddress);

    }

    public void login(String username, String password) throws IOException, LoginFailException
    {
        username += "\r";
        password += " -c\r";
        send(username);
        send(password);

        readLine();
        readLine();
        String string = readLine();

        if (!string.contains("American Power Conversion"))
        {
            throw new LoginFailException();
        }

        while (!(string = readLine()).contains("APC>"))
        {
        }
        System.out.println("success");
    }

    public void send(String output) throws IOException
    {
        printWriter.print(output);
        printWriter.flush();

    }

    public String readLine() throws IOException
    {
        return connectionReceiveThread.getCurrentLine();
    }

    public InputStream getInputStream()
    {
        return telnetClient.getInputStream();
    }

    public void disconnect() throws IOException
    {
        send("logout\r");
        telnetClient.disconnect();
        logger.info("disconnected from " + ipAddress);
    }

}
