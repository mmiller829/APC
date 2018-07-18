package apc;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketStateTask extends Task
{

    private final SocketStateManager socketStateManager;
    private final Connection connection;
    private StringBuilder builder;
    private final ArrayList<Socket> sockets;
    private final Logger logger = LogManager.getLogger(SocketStateTask.class);

    public SocketStateTask(String command, SocketStateManager socketStateManager, Connection connection)
    {
        super(command);
        this.socketStateManager = socketStateManager;
        this.connection = connection;
        sockets = new ArrayList();
    }

    @Override
    public void execute()
    {
        try
        {
            builder = new StringBuilder();
            connection.send(getCommand() + "\r");
            
            String response = connection.readResponse();
            parse(response);
            socketStateManager.updateSocketDisplay(sockets);
        }
        catch (IOException ex)
        {

        }
    }

    private void parse(String response)
    {
        String[] status = response.split("\r");

        for (String line : status)
        {
            String[] splitLine = line.split(":");
            String socketNumber = splitLine[0].trim();

            switch (socketNumber)
            {
                case "1":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "2":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "3":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "4":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "5":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "6":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "7":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "8":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "9":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "10":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "11":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "12":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "13":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "14":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "15":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "16":
                {
                    sockets.add(createSocket(socketNumber, splitLine[1].trim(), splitLine[2].trim()));
                    break;
                }
                case "E100":
                {
                    logger.error("Status command not received");
                    break;
                }
                default:
                {
                    //do nothing
                }
            }
        }
    }

    private Socket createSocket(String number, String state, String name)
    {
        return new Socket(number, state, name);
    }

}
