package life;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LifeSim extends JFrame {

    public byte c10;
    //public byte c22;
    public Color c11;
    public Color c22;
    private static final long serialVersionUID = 3400265056061021539L;

    private LifeRandom lifeRandom = null;
    private LifePanel lifePanel = null;
    private JButton button1 = null;
    private JButton button2 = null;
    private JButton button3 = null;
    private JButton button4 = null;
    private JSlider slider = null;

    public LifeSim(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        lifePanel = new LifePanel();
        // размеры поля
        lifePanel.initialize(160, 82);
        add(lifePanel);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        add(toolBar, BorderLayout.NORTH);

        button1 = new JButton("Запустить");
        toolBar.add(button1);
        button2 = new JButton("Очистить поле");
        toolBar.add(button2);
       /* button3 = new JButton("Male");
        toolBar.add(button3);
        button4 = new JButton(("Female"));
        toolBar.add(button4);*/

        // бегунок, регулирующий скорость симуляции (задержка в мс между шагами
        // симуляции)
        slider = new JSlider(1, 200);
        slider.setValue(50);
        lifePanel.setUpdateDelay(slider.getValue());
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lifePanel.setUpdateDelay(slider.getValue());
            }
        });

        toolBar.addSeparator();
        toolBar.add(new JLabel(" Быстро"));
        toolBar.add(slider);
        toolBar.add(new JLabel("Медленно"));

        // запуск/остановка симуляции; попутно меняется надпись на кнопке
        lifeRandom = new LifeRandom();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lifePanel.isSimulating()) {
                    lifePanel.stopSimulation();
                    button1.setText("Запустить");
                    lifeRandom.check(lifePanel.c);
                } else {
                    lifePanel.startSimulation();
                    button1.setText("Остановить");
                }
            }
        });
        // очистка поля
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (lifePanel.getLifeModel()) {
                    lifePanel.getLifeModel().clear();
                    lifePanel.repaint();
                }
            }
        });
        /*button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lifePanel.isSimulating()){
                    lifePanel.stopSimulation();
                    c11 = lifePanel.c1;
                    c10 = 1;
                }

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c22 = lifePanel.c2;
                c10 = 0;
            }
        });*/
        button1.setMaximumSize(new Dimension(100, 50));
        button2.setMaximumSize(new Dimension(100, 50));
        /*button3.setMaximumSize(new Dimension(100, 50));
        button4.setMaximumSize(new Dimension(100, 50));*/
        slider.setMaximumSize(new Dimension(300, 50));
        pack();
        setVisible(true);
    }

    public static void main(String... args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LifeSim("LifeSim");
            }
        });
    }
}