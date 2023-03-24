import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;

public class mainPage extends JFrame{
  mainPage(){
    JFrame frame = new JFrame();
    JLabel label = new JLabel("銀行管理");
    label.setForeground(Color.white);
    label.setFont(new Font("Lato", Font.BOLD, 30));
    label.setBounds(125,75,200,50);
    add(label);
    final int width = 125;
    final int height = 50;
    final int row1 = 175;
    final int row2 = 300;

    setTitle("Main Page");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(750, 500);
    setLayout(null);
    setVisible(true);
    Color bgcolor = new Color(36,37,130);
    getContentPane().setBackground(bgcolor);

    JButton depositButton = new JButton("入金");
    depositButton.setBounds(125, row1, width,height);
    depositButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inputbox = JOptionPane.showInputDialog("ユーザーを検索");
        String value = JOptionPane.showInputDialog("入金額");
        Double newval = Double.parseDouble(value);
        userInfo myHashTable = new userInfo();
        Hashtable<String, Double> table = myHashTable.getHashtable();
        myHashTable.setDeposit(table, inputbox,newval);
        JOptionPane.showMessageDialog(null,inputbox+"に: ¥" + newval + "追加されました","合計金額",JOptionPane.PLAIN_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }
      
    });
    add(depositButton);

    JButton withdrawButton = new JButton("引き出し");
    withdrawButton.setBounds(275, row1, width,height);
    withdrawButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String inputbox = JOptionPane.showInputDialog("ユーザーを検索");
        String value = JOptionPane.showInputDialog("出金額");
        Double newval = Double.parseDouble(value);
        userInfo myHashTable = new userInfo();
        Hashtable<String, Double> table = myHashTable.getHashtable();
        myHashTable.setWithdrawal(table, inputbox,newval);
        JOptionPane.showMessageDialog(null,inputbox+"に: ¥" + newval + "引き出されました","合計金額",JOptionPane.PLAIN_MESSAGE);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }
      
    });
    add(withdrawButton);


    JButton search = new JButton("金額検索");
    search.setBounds(425, row1, width,height);
    search.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
          String inputbox = JOptionPane.showInputDialog("ユーザーを検索");
          userInfo myHashTable = new userInfo();
          Hashtable<String, Double> table = myHashTable.getHashtable();
          Double tablefunc = myHashTable.getAns(table, inputbox);
          JOptionPane.showMessageDialog(null,inputbox + ": ¥"+ tablefunc,"合計金額",JOptionPane.PLAIN_MESSAGE);
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }
    });
    add(search);

    JButton totalnumButton = new JButton("合計金額");
    totalnumButton.setBounds(125, row2, width,height);
    totalnumButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        userInfo Hashtable = new userInfo();
        Hashtable<String, Double> table = Hashtable.getHashtable();
        double sum = Hashtable.sumHashTableValues(table);
        JOptionPane.showMessageDialog(null,"¥"+sum,"合計金額",JOptionPane.PLAIN_MESSAGE); 
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }
      
    });
    add(totalnumButton);

    JButton createfile = new JButton("ファイル作成");
    createfile.setBounds(275, row2, width,height);
    createfile.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {
        userInfo Hashtable = new userInfo();
        Hashtable<String, Double> table = Hashtable.getHashtable();
        double sum = Hashtable.sumHashTableValues(table);
        File file = new File("金額まとめ.txt");
        try (FileOutputStream fos = new FileOutputStream(file); OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");) {
          osw.write("金額まとめ\n合計金額: "+ sum +"円\n"  + table);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }});
    add(createfile);

    JButton addUser = new JButton("ユーザーを追加");
    addUser.setBounds(425,row2,width, height);
    addUser.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        userInfo Hashtable = new userInfo();
        String inputbox = JOptionPane.showInputDialog("ユーザー名");
        String value = JOptionPane.showInputDialog("金額");
        Double newval = Double.parseDouble(value);
        Hashtable.addnewUser(inputbox, newval);
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
      }
      
    });
    add(addUser);

    JPanel leftPanel = new JPanel();
    leftPanel.setBackground(Color.white);
    Border black = BorderFactory.createLineBorder(Color.black, 2);
    leftPanel.setBorder(black);
    leftPanel.setBounds(0,0,30,463);
    add(leftPanel);
  }
  public static void main(String args[]) {
    mainPage mainPage = new mainPage();
  }
}
