import javax.swing.*;
public class Initial extends JFrame {
    //characteristics of the new frame
    Initial(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setJMenuBar(new menu());
        setVisible(true);
    }
    //the actual new game
    public void start(String type, int m, int n){
        Play name= new Play(type,m,n);
        setContentPane(name);
        pack();
        setSize(500,500);
    }
    public void WinLose(){
        if (Play.moves==0){
            JOptionPane.showMessageDialog(null, "YOU LOST!","Don't be sad!", JOptionPane.PLAIN_MESSAGE);
            dispose();
        }else if(Play.target<= Play.sum){
            JOptionPane.showMessageDialog(null, "YOU WON!",":)", JOptionPane.PLAIN_MESSAGE);
            dispose();
        }
    }
}
