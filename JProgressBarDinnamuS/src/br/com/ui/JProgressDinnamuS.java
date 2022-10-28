/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ui;

/**
 *
 * @author dti
 */
 




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JProgressDinnamuS extends JPanel
                             implements ActionListener,
                                        PropertyChangeListener {

    private JProgressBar progressBar;
    private ArrayList<String> arLista;
    //private JButton startButton;
    private JTextArea taskOutput;
    private Task task;
    private int valorprogress =0;
    private int Maximo=0;
    private String MensagemProcessamento="";


    public String getMensagemProcessamento() {
        return MensagemProcessamento;
    }

    /**
     * @param MensagemProcessamento the MensagemProcessamento to set
     */
    public void setMensagemProcessamento(String MensagemProcessamento) {
        this.MensagemProcessamento = MensagemProcessamento;
    }

    /**
     * @return the arLista
     */
    public ArrayList<String> getArLista() {
        return arLista;
    }

    /**
     * @param arLista the arLista to set
     */
    public void setArLista(ArrayList<String> arLista) {
        this.arLista = arLista;
        setMaximo(arLista.size());
    }

    /**
     * @return the Maximo
     */
    public int getMaximo() {
        return Maximo;
    }

    /**
     * @param Maximo the Maximo to set
     */
    public void setMaximo(int Maximo) {
        this.Maximo = Maximo;
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */

        public Task()
        {
            
        }
        @Override
        public Void doInBackground() {
                
                valorprogress ++;//= random.nextInt(10);
                setProgress(valorprogress);
                return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            //startButton.setEnabled(true);
            setCursor(null); //turn off the wait cursor
            Log(" - OK\n");
            //System.out.print("Registro "  + valorprogress + "\n");

        }
    }
    public void Log(String cMsg){ Log(cMsg,false);  }
    public void Log(String cMsg, boolean  bUsarDataHora )
    {
        taskOutput.append( ( bUsarDataHora ? (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date()) :"") + " " +cMsg);
    }
    public JProgressDinnamuS(){
            initComponents();
    }

    public void initComponents() {
        //super(new BorderLayout());

        //JFrame frame = new JFrame("ProgressBarDemo");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        progressBar = new JProgressBar(0, getMaximo());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        taskOutput = new JTextArea(5, 20);
        taskOutput.setMargin(new Insets(5,5,5,5));
        taskOutput.setEditable(false);

        JPanel panel = new JPanel();
        //panel.add(startButton);
        progressBar.setSize(panel.getHeight(), panel.getWidth());
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskOutput), BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.setOpaque(true); //content panes must be opaque
        //frame.setContentPane(this);

/*
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(taskOutput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(taskOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGap(2, 2, 2))
        );*/

        //Display the window.
        //frame.pack();
        //frame.setVisible(true);

    }

    /**
     * Invoked when the user presses the start button.
     */
    public void AtualizarBarra() {
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    public void actionPerformed(ActionEvent evt) {}
    
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);       
            Log(arLista.get(progress-1), true);
        }
    }    

}

