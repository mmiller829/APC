package apc;

import interfaces.TaskExecutorRunnable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskExecutorThread implements TaskExecutorRunnable
{

    private final ConcurrentLinkedQueue<Task> taskQueue;
    private volatile boolean running = false;

    public TaskExecutorThread()
    {
        taskQueue = new ConcurrentLinkedQueue();
    }

    @Override
    public void run()
    {
        while (running && !taskQueue.isEmpty())
        {
            Task task = taskQueue.remove();
            task.execute();
        }
        running = false;
    }

    @Override
    public void add(Task task)
    {
        taskQueue.add(task);
    }

    @Override
    public void start()
    {
        if (!running)
        {
            Thread thread = new Thread(this, "Thread executor");
            running = true;
            thread.start();
        }
    }

    @Override
    public void stop()
    {
        running = false;
    }

}
