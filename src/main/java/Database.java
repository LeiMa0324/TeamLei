import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection = null;

    public static void initDatabase() {
        // Make sure the Java DB driver is installed as a dependency (prints instructions to set up if not installed properly)
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Java DB Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the Java JDK is installed");
            System.out.println("Select the folder java/jdk1.8.xxx/Database/lib where xxx is the version.");
            System.out.println("Click OK, compile the code and run it.");
            e.printStackTrace();
            return;
        }

        //检查mydb是否存在
        boolean databseExists = new File("mydb/").exists();
        System.out.println(databseExists);

        // 创建一个connect to mydb
        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:mydb;create=true");
        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }

        // 如果db不存在，则从csv中load data
        if(!databseExists){
            System.out.println("Database not found, loading data from csv");
            // UNCOMMENT THE BELOW LINE TO RESET THE DATABASE
            //dropTables();
            createTables();     //创建所有表格
            // load in .csv files to the database
            // nodes
            Database.loadFromCSV("node", "nodes.csv");  //将用户csv load入表格中

            // edges
            Database.loadFromCSV("edge", "edges.csv");   //将edge csv load入表格中
        }
    }

    /**
     * 批量创建表， initialize database
     */
    private static void createTables(){

        // User table
        String userCreation =     "CREATE TABLE nodes (" +
                "nodeID VARCHAR(50) primary key, " +
                "xcoord INTEGER not null, " +
                "ycoord INTEGER not null, " +
                "xcoord3d INTEGER not null, " +
                "ycoord3d INTEGER not null, " +
                "floor VARCHAR(50) not null, " +
                "building VARCHAR(50) not null, " +
                "nodeType VARCHAR(50) not null, " +
                "longName VARCHAR(200) not null, " +
                "shortName VARCHAR(50) not null" +
                ")";
        createTable("User", userCreation);

        // Node table

        /**
         * Edge table
         */

        /**
         *Servide Request
         */

    }

    /**
     * 创建一个表格
     * @param tableName 表名
     * @param sql   sql语句
     */
    private static void createTable(String tableName, String sql){

    }

    /**
     * 将csv load入表格中
     */
    private static void loadFromCSV(String tableName, String csvFile){

    }
}
