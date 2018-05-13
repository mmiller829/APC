package gui;

import apc.SocketStateManager;
import apc.Task;
import apc.TaskFactory;
import interfaces.SocketStateDisplay;

public class MainFrame extends javax.swing.JFrame implements SocketStateDisplay
{

    public MainFrame()
    {
        initComponents();

    }

    private void socketStateTaskThread()
    {
        SocketStateManager socketStateManager = new SocketStateManager();
        socketStateManager.register(this);
        final Task socketStateTask = TaskFactory.createSocketStateTask(socketStateManager);
        
        Thread thread = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                // add task to execute every x seconds
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        thread.start();

    }

    @Override
    public void updateSocketState()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
