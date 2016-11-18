import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Виталий on 14.10.2016.
 */
public class FisherMainFrame extends JFrame {
    private final int FONT_SIZE = 60;
    private JPanel positionPanel;
    private JPanel mainPanel;
    private JLabel positionLabel = new JLabel();
    private String position;
    private final String PROGRAMM_VERSION = "v1.1.0";

    public FisherMainFrame() throws HeadlessException {
        setTitle("   fisher960generator " + PROGRAMM_VERSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMenu();

        Container c = getContentPane();
        mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        position = (GeneratePosition.getStartPosition());
        positionPanel = createPanel(new TitledBorder("Позиция"), position);
        mainPanel.add(positionPanel);

        mainPanel.add(Box.createVerticalStrut(12));

        //создание кнопки
        JButton button = new JButton("Generate");
        button.setMnemonic('G');
        button.addActionListener(new GeneratePositionAction());
        button.setAlignmentX(button.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        mainPanel.add(button);

        c.add(mainPanel);
        pack();
        setResizable(false);
        setVisible(true);
    }

    private void setMenu(){

        JMenuBar menuBar = new JMenuBar();
        JMenu help = new JMenu("Справка");
        JMenuItem about = new JMenuItem("О программе");
        about.addActionListener(new ActionListener() {
            private String messageHeader = "О программе";
            private String message = "fisher960generator " + PROGRAMM_VERSION +"\n\n" +
                    "Программа случайным образом\n" +
                    "генерирует позиции Фишера.\n\n" +
                    "Автор: Виталий Ивановский\n" +
                    "e-mail: ivanovskij.vitalij@yandex.ua";
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(FisherMainFrame.this,
                        message, messageHeader,
                        JOptionPane.INFORMATION_MESSAGE, null);
            }
        });
        help.add(about);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(help);
        setJMenuBar(menuBar);
    }

    private JPanel createPanel(Border b, String text){
        JPanel panel = new JPanel(new BorderLayout());
        setLabel(text);
        panel.setBorder(new CompoundBorder(b, new EmptyBorder(12, 12, 12, 12)));
        panel.add(positionLabel);
        return panel;
    }
    private void setLabel(String text){
        Font font = new Font("Times", Font.PLAIN, FONT_SIZE);
        positionLabel.setText(text);
        positionLabel.setFont(font);
    }

    private class GeneratePositionAction extends AbstractAction {
        public GeneratePositionAction(){}
        public void actionPerformed(ActionEvent event) {
            resetPosition();
            setPosition();
        }


        private void setPosition(){
            position = GeneratePosition.generatePosition();
            setLabel(position);
        }

        private void resetPosition(){
            GeneratePosition.resetPosition();
        }
    }

    public static void main(String[] args) {
        new FisherMainFrame();
    }
}
