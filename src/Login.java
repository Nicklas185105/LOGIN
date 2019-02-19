import java.sql.*;

public class Login {

    public static void main (String[] args){Connect();}

    static void Connect(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185105?"
                + "user=s185105&password=HzlMdPaCaRY0xr7mRHVhd")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login");
            System.out.println("Got resultset from database:");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
