import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
class Play extends JPanel {
    public static Random random = new Random();
    static int moves;
    static int target;
    static int sum;
    int m;
    int n;
    String type;
    JLabel movesLabel;
    JLabel sumLabel;
    JPanel panelGrid = new JPanel();
    JPanel panelUp = new JPanel();
    JPanel panelDown = new JPanel();
    JPanel panelOperations = new JPanel();
    JButton[][] buttons;
    JLabel[] operationsLabels;
    JButton temp_btn=new JButton();
    String temp_text;

    Play(String type, int m, int n){
        setBackground(new Color(140, 0, 225));
        setLayout(new BorderLayout());
        this.type =type;
        this.m=m;
        this.n=n;
        CreatePanelNxM();
        Up();
        Down();
    }
    public void Operations(int m){
        for (int i = 0; i < m; i++) {
            JLabel oper = new JLabel(get_operation());

            oper.setOpaque(true);
            oper.setBackground(new Color(114, 161, 229));
            oper.setBorder(new EmptyBorder(10,10,10,10));
            oper.setForeground(Color.WHITE);
            oper.setFont(new Font("Monospace", Font.PLAIN,25 ));

            if(i==0) {
                oper.setBackground(new Color(114, 190, 229));
            }

            operationsLabels[i]=oper;
            panelOperations.add(operationsLabels[i]);
        }
    }
    public static String get_operation(){
        String[] ops={"/","*","-","+"};
        return ops[random.nextInt(4)];
    }
    public void Up(){
        panelUp.setLayout(new BorderLayout());
        JLabel targetLabel = new JLabel("Target: "+target);
        movesLabel = new JLabel("Moves left: "+moves);

        targetLabel.setFont(new Font("Monospace", Font.PLAIN,20 ));
        movesLabel.setFont(new Font("Monospace", Font.PLAIN,20 ));
        targetLabel.setForeground(Color.WHITE);
        movesLabel.setForeground(Color.WHITE);

        panelUp.setBorder(new EmptyBorder(12,12,12,12));
        panelUp.setBackground(new Color(140, 0, 225));
        panelUp.add(targetLabel,BorderLayout.LINE_START);
        panelUp.add(movesLabel,BorderLayout.LINE_END);
        add(panelUp,BorderLayout.NORTH);
    }
    public void Down(){
        sumLabel = new JLabel("Sum: "+sum);

        sumLabel.setFont(new Font("Monospace", Font.PLAIN,20 ));
        sumLabel.setForeground(Color.WHITE);
        panelDown.setBorder(new EmptyBorder(10,10,10,10));
        panelDown.setBackground(new Color(140, 0, 225));
        panelDown.add(sumLabel);
        add(panelDown,BorderLayout.SOUTH);
    }
    public void gridMxN() {
        operationsLabels = new JLabel[m];
        buttons = new JButton[m][n];
        panelOperations(m);
        GridLayout gl = new GridLayout(m, n);
        panelGrid.setLayout(gl);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int rand_num = random.nextInt(10);
                sum += rand_num;
                JButton b = new JButton("" + rand_num);

                b.setFont(new Font("Monospace", Font.PLAIN,30 ));
                b.setForeground(Color.WHITE);
                b.setBorder(new LineBorder(new Color(114, 161, 229)));
                b.setBackground(new Color(138, 123, 229));

                buttons[i][j]=b;
                panelGrid.add(b);

                b.addActionListener(e ->
                {
                    JButton new_button = (JButton) e.getSource();
                    String new_button_text = new_button.getText();
                    int row=0, column=0;

                    //find row and column of button
                    for (int k = 0; k < buttons.length; k++) {
                        for (int l = 0; l < buttons[k].length; l++) {
                            if (buttons[k][l]==new_button) {
                                row = k;
                                column=l;
                            }
                        }
                    }
                    for (int k = 0; k < buttons.length; k++) {
                        for (int l = 0; l < buttons[k].length; l++) {
                            //row and column of pressed button
                            if (k==row || l==column){
                                buttons[k][l].setEnabled(true);
                                buttons[k][l].setBackground(new Color(143, 99, 229));
                            }else{
                                buttons[k][l].setEnabled(false);}
                                buttons[k][l].setBackground(new Color(138, 123, 229));
                        }
                    }
                    //if pressed button exist
                    if (temp_text != null){
                        int new_value=0;
                        switch (operationsLabels[0].getText()){
                            case "+":
                                new_value=(Integer.parseInt(temp_text)+Integer.parseInt(new_button_text))%10;
                                break;
                            case "-":
                                new_value=(Integer.parseInt(temp_text)-Integer.parseInt(new_button_text))%10;
                                break;
                            case "*":
                                new_value=(Integer.parseInt(temp_text)*Integer.parseInt(new_button_text))%10;
                                break;
                            case "/":
                                //just in case if it is zero
                                try{
                                    new_value=(Integer.parseInt(temp_text)/Integer.parseInt(new_button_text))%10;
                                }catch (Exception ignored){};
                                break;
                        }
                        //changing the operation
                        for (int k = 0; k < operationsLabels.length; k++) {
                            if (k==operationsLabels.length-1)
                                operationsLabels[operationsLabels.length-1].setText(get_operation());
                            else
                                operationsLabels[k].setText(operationsLabels[k+1].getText());
                        }
                        //using the absolute value of the new value, so we won't have negative integers
                        new_value=Math.abs(new_value);
                        temp_btn.setText(""+new_value);
                        sum+=new_value;
                        sumLabel.setText("Sum: "+sum);
                        moves--;
                        movesLabel.setText("Moves left: "+moves);
                        Main.main_frame.WinLose();
                    }
                    //temp_btn is used as the before pressed button
                    temp_text=new_button_text;
                    temp_btn=new_button;
                });
            }
        }
        if(!(type.equals("User Input"))) target=(sum+random.nextInt(25,80));

        panelGrid.setBorder(new LineBorder(new Color(114, 161, 229)));
        panelGrid.setBorder(BorderFactory.createLineBorder(Color.white));

        add(panelGrid,BorderLayout.CENTER);
    }
    public void panelOperations(int m){
        panelOperations.setLayout(new GridLayout(m,1));
        Operations(m);

        panelOperations.setBorder(BorderFactory.createLineBorder(Color.white));
        add(panelOperations, BorderLayout.EAST);
    }
    public void CreatePanelNxM() {
        switch (this.type) {
            case "User Input":
                sum=0;
                try{
                    moves =Integer.parseInt(JOptionPane.showInputDialog(null, "Input moves if you want a different number:", "", JOptionPane.QUESTION_MESSAGE));
                    target =Integer.parseInt(JOptionPane.showInputDialog(null, "Input target if you want a different number:", "", JOptionPane.QUESTION_MESSAGE));
                }catch (NumberFormatException ignored){}
                break;
            case "Random":
                sum=0;
                target=0;
                moves=random.nextInt(18,50);
                this.m=random.nextInt(2,10);
                this.n=random.nextInt(2,10);
                break;
            case "Difficulty Easy":
                sum=0;
                target=0;
                moves=30;
                this.m=7;
                this.n=7;
                break;
            case "Difficulty Medium":
                sum=0;
                target=0;
                moves=20;
                this.m=5;
                this.n=5;
                break;
            case "Difficulty Hard":
                sum=0;
                target=0;
                moves=10;
                this.m=3;
                this.n=3;
                break;
            }
            gridMxN();
    }
}