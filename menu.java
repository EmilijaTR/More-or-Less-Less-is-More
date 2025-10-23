import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class menu extends JMenuBar implements ActionListener {
    menu() {
        JMenu menu = new JMenu("New game");
        
        JMenu submenu = new JMenu("Difficulty");
        JMenuItem i1 = new JMenuItem("User input");
        JMenuItem i2 = new JMenuItem("Random");
        JMenuItem i3 = new JMenuItem("Easy");
        JMenuItem i4 = new JMenuItem("Medium");
        JMenuItem i5 = new JMenuItem("Hard");

        i1.setActionCommand("User Input");
        i1.addActionListener(this);
        i2.setActionCommand("Random");
        i2.addActionListener(this);
        i3.setActionCommand("Difficulty Easy");
        i3.addActionListener(this);
        i4.setActionCommand("Difficulty Medium");
        i4.addActionListener(this);
        i5.setActionCommand("Difficulty Hard");
        i5.addActionListener(this);

        menu.add(i1);
        menu.add(i2);
        submenu.add(i3);
        submenu.add(i4);
        submenu.add(i5);
        menu.add(submenu);
        add(menu);

        setBackground(new Color(119, 0, 200));
        setBorder(new EmptyBorder(4,6,4,4));
        menu.setFont(new Font("Monospace", Font.BOLD,13 ));
        menu.setForeground(new Color(229, 218, 218));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "User Input":
                try {
                    Main.main_frame.start("User Input", Integer.parseInt(JOptionPane.showInputDialog(null, "Input row:", "", JOptionPane.QUESTION_MESSAGE)), Integer.parseInt(JOptionPane.showInputDialog(null, "Input column:", "", JOptionPane.QUESTION_MESSAGE)));
                }catch (NumberFormatException ignored){}
                break;
            case "Random":
            case "Difficulty Easy":
            case "Difficulty Medium":
            case "Difficulty Hard":
                Main.main_frame.start(e.getActionCommand(),-1,-1);
                break;
        }
    }
}