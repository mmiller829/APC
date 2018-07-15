
package interfaces;

import apc.Task;

public interface TaskExecutorRunnable extends Runnable
{
    public void add(Task task);
    public void start();
    public void stop();   
}
