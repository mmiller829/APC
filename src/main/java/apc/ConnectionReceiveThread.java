package apc;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionReceiveThread implements Runnable
{

    private volatile boolean running;
    private final Connection connection;
    private final InputStream inputStream;
    private final ConcurrentLinkedQueue<String> stringQueue;
    private String currentLine = "";

    static final Logger logger = LogManager.getLogger(ConnectionReceiveThread.class);

    public ConnectionReceiveThread(Connection connection)
    {
        this.connection = connection;
        inputStream = connection.getInputStream();

        stringQueue = new ConcurrentLinkedQueue();
        running = false;
    }

    @Override
    public void run()
    {
        while (running)
        {
            try
            {
                StringBuilder line = new StringBuilder();

                byte[] buffer = new byte[1024];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(buffer)) > 0)
                {

                    String input = new String(buffer, 0, bytesRead);

                    line.append(input);

                    if (line.toString().contains("\r"))
                    {
                        break;
                    }

                }

                stringQueue.add(line.toString());

            }
            catch (IOException ex)
            {
                stop();
                logger.debug("Inputstream has been closed");

            }
        }
    }

    public String getCurrentLine()
    {
        while (stringQueue.isEmpty())
        {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException ex)
            {
                java.util.logging.Logger.getLogger(ConnectionReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stringQueue.remove();
    }

    public void start()
    {
        if (!running)
        {
            Thread thread = new Thread(this);
            running = true;
            thread.start();
        }
    }

    public void stop()
    {
        running = false;
    }

}
