import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginPage extends JFrame {
  public void initialize() {
    
    JLabel label = new JLabel("銀行管理");
    JButton loginButton = new JButton("ログイン");
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel userLabel = new JLabel("ユーザー名");
    JLabel userPassword = new JLabel("パスワード");

    label.setBounds(170,25,120,25);
    userLabel.setBounds(50,100,75,25);
    userPassword.setBounds(50,150,75,25);
    username.setBounds(125,100,200,25);
    password.setBounds(125,150,200,25);
    userLabel.setForeground(Color.white);
    label.setForeground(Color.white);
    label.setFont(new Font("Lato", Font.BOLD, 20));
    userPassword.setForeground(Color.white);

    loginButton.setBounds(125, 200, 100, 25);
    loginButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton){
          String user = username.getText();
          String pass = String.valueOf(password.getPassword());
          loginInfo userInfo = new loginInfo();
          loginInfo passInfo = new loginInfo();
          if(user.equals(userInfo.getUser())&& pass.equals(passInfo.getPass())){
            mainPage mainPage = new mainPage();
            dispose();
          }
          else{
            JOptionPane.showMessageDialog(null,"Unknown user","Error",JOptionPane.PLAIN_MESSAGE); 
          }
        }
       
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }
    });

    setTitle("Login Form");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(420, 320);
    setLayout(null);
    setVisible(true);
    Color bgcolor = new Color(36,37,130);
    getContentPane().setBackground(bgcolor);
    add(label);
    add(userLabel);
    add(userPassword);
    add(username);
    add(password);
    add(loginButton);
  }
  public static void main(String args[]) {
    loginPage loginform = new loginPage();
    loginform.initialize();
  }
}