
package apc;

import interfaces.TaskExecutorRunnable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskExecutorThread implements TaskExecutorRunnable
{
    private final ConcurrentLinkedQueue<Task> taskQueue;
    
    public TaskExecutorThread()
    {
        taskQueue = new ConcurrentLinkedQueue();
    }

        @Override
    public void run()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void add(Task task)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyStart()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void start()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
