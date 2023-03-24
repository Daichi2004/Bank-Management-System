import java.util.*;
import java.lang.Math;
import java.sql.SQLException;
import java.sql.*;

public class userInfo {
  Connection conn = null;
  private Hashtable<String, Double> userInfo = new Hashtable<>(10);

  public static void main(String args[]) throws SQLException {

  }

  public userInfo() {
    try {
      Properties props = new Properties();
      props.load(DatabaseConnection.class.getResourceAsStream("/db.properties"));
      String url = props.getProperty("db.url");
      String username = props.getProperty("db.username");
      String password = props.getProperty("db.password");

      Class.forName(props.getProperty("db.driver"));
      conn = DriverManager.getConnection(url, username, password);

      String query = "SELECT Username, Deposit FROM userinformation";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        String id = rs.getString("Username");
        Double name = rs.getDouble("Deposit");

        userInfo.put(id, name);
      }

      rs.close();
      stmt.close();
      conn.close();

      System.out.println(userInfo);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  protected Double getAns(Hashtable<String, Double> userInfo, String key) {
    if (userInfo.containsKey(key)) {
      return userInfo.get(key);
    } else {
      return null;
    }
  }

  public double sumHashTableValues(Hashtable<String, Double> hashtable) {
    return sumHashTableValuesHelper(hashtable.keys(), hashtable);
  }

  private double sumHashTableValuesHelper(Enumeration<String> keys, Hashtable<String, Double> hashtable) {
    if (!keys.hasMoreElements()) {
      return 0.0;
    }
    String key = keys.nextElement();
    double value = hashtable.get(key);
    return value + sumHashTableValuesHelper(keys, hashtable);
  }

  protected Hashtable<String, Double> getHashtable() {
    return userInfo;
  }

  protected void setDeposit(Hashtable<String, Double> info, String key, Double add) {
    double addednum;
    if (info.containsKey(key)) {
      addednum = add + info.get(key);
    } else {
      addednum = info.get(key);
    }
    Connection conn = null;
    try {
      Properties props = new Properties();
      props.load(DatabaseConnection.class.getResourceAsStream("/db.properties"));
      String url = props.getProperty("db.url");
      String username = props.getProperty("db.username");
      String password = props.getProperty("db.password");

      conn = DriverManager.getConnection(url, username, password);

      String query = "UPDATE userinformation SET Deposit = ? WHERE Username = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setDouble(1, addednum);
      stmt.setString(2, key);
      int rowsAffected = stmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) affected");

      stmt.close();
      conn.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  protected void setWithdrawal(Hashtable<String, Double> info, String key, Double sub) {
    double subtractednum;
    if (info.containsKey(key) && sub <= info.get(key)) {
      subtractednum = info.get(key) - sub;
    } else {
      subtractednum = info.get(key);
    }
    Connection conn = null;
    try {
      Properties props = new Properties();
      props.load(DatabaseConnection.class.getResourceAsStream("/db.properties"));
      String url = props.getProperty("db.url");
      String username = props.getProperty("db.username");
      String password = props.getProperty("db.password");

      conn = DriverManager.getConnection(url, username, password);

      String query = "UPDATE userinformation SET Deposit = ? WHERE Username = ?";
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setDouble(1, subtractednum);
      stmt.setString(2, key);
      int rowsAffected = stmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) affected");

      stmt.close();
      conn.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  protected void addnewUser(String user, Double money) {
    Connection conn = null;
    try {
      Properties props = new Properties();
      props.load(DatabaseConnection.class.getResourceAsStream("/db.properties"));
      String url = props.getProperty("db.url");
      String username = props.getProperty("db.username");
      String password = props.getProperty("db.password");

      Class.forName(props.getProperty("db.driver"));
      conn = DriverManager.getConnection(url, username, password);

      System.out.println("Connected to the database");
      String sql = "INSERT INTO userinformation (Username, Deposit) VALUES('" + user + "' , '" + money + "')";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.executeUpdate();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }
}
