package site.cilicili.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import site.cilicili.common.db.domain.dto.DatabaseConnectionDto;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.dbUtils
 * Date:2023/12/5 19:41
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public class DbUtils {
    private static final String PREFIX = "jdbc:mysql://%1$s:%2$s";
    private static final String CREATEDB = "CREATE DATABASE IF NOT EXISTS `%1$s` CHARACTER SET utf8 COLLATE utf8_general_ci;";
    private static final String QUERYLIST = "SELECT id,url,username,password,driver,scheme FROM database_connection where scheme = '%1$s';";
    private static final String UPDATELIST = "UPDATE database_connection set url = '%2$s',username='%3$s',password='%4$s',driver='%5$s'  where scheme = '%1$s';";
    private static final String INSERTONE = "INSERT INTO `%1$s`.database_connection VALUES(DEFAULT,'%2$s','%3$s','%4$s','%5$s','%6$s')";
    private static final String DBCONFIG = "dbConfig.yaml";
    private static final String filePath = "gin-quasar-admin.sql";
    private static final Logger LOGGER = LoggerFactory.getLogger(DbUtils.class);
    private static final Map<String, String> DRIVER_CLASS_MAP = new HashMap<>();

    static {
        // Initialize the map with supported database types and their corresponding driver classes
        DRIVER_CLASS_MAP.put("mysql", "com.mysql.cj.jdbc.Driver");
        DRIVER_CLASS_MAP.put("postgresql", "org.postgresql.Driver");
        DRIVER_CLASS_MAP.put("oracle", "oracle.jdbc.driver.OracleDriver");
        // Add other supported database types and their driver classes here
        // DRIVER_CLASS_MAP.put("another_database_type", "another_driver_class");
    }

    public static String getDriverClass(String dbType) {
        String driverClass = DRIVER_CLASS_MAP.get(dbType.toLowerCase());
        if (driverClass != null) {
            return driverClass;
        } else {
            throw new IllegalArgumentException("Unsupported database type: " + dbType);
        }
    }

    public static DatabaseConnection executeScript(DatabaseConnectionDto databaseConnectionDto, String key) throws SQLException, IOException {
        if (databaseConnectionDto == null) {
            return null;
        }
        // Read the SQL script from the file
        final String format = String.format(PREFIX, databaseConnectionDto.getDbHost(), databaseConnectionDto.getDbPort());
        final String dbPassword = databaseConnectionDto.getDbPassword();
        final String dbUser = databaseConnectionDto.getDbUser();
        // Establish a connection
        Connection connection = DriverManager.getConnection(format, dbUser, dbPassword);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        final String dbSchema = databaseConnectionDto.getDbSchema();
        // Execute the SQL script
        final String driver = getDriverClass(databaseConnectionDto.getDbType().toLowerCase(Locale.ROOT));
        final String format1 = String.format(INSERTONE, dbSchema, format, dbUser, dbPassword, driver, dbSchema);
        final String format2 = String.format(UPDATELIST, dbSchema, format, dbUser, dbPassword, driver);
        if (ObjectUtil.isNull(checkDb(key))) {
            statement.executeUpdate(String.format(CREATEDB, dbSchema));
            statement.executeUpdate(String.format("use `%1$s`;", dbSchema));
            runSqlByReadFileContent(connection, Resources.getResourceAsStream(filePath));
            databaseConnectionDto.setDriver(driver);
            databaseConnectionDto.setUrl(format);
            databaseConnectionDto.setScheme(dbSchema);
            writeConfig(databaseConnectionDto, key);
        }
        if (databaseConnection(connection, dbSchema) == null) {
            statement.execute(format1);
        } else {
            statement.executeUpdate(format2);
        }
        final DatabaseConnection databaseConnection = databaseConnection(connection, dbSchema);
        connection.commit();
        statement.close();
        connection.close();
        return databaseConnection;
    }

    public static boolean databaseExists(Connection connection, String databaseName) throws SQLException {
        // Check if the database already exists
        ResultSet resultSet = connection.getMetaData().getCatalogs();
        while (resultSet.next()) {
            String existingDatabase = resultSet.getString(1);
            if (databaseName.equalsIgnoreCase(existingDatabase)) {
                resultSet.close();
                return true;
            }
        }
        resultSet.close();
        return false;
    }

    public static DatabaseConnectionDto checkDb(String key) {
        final String filePath = new ClassPathResource(DBCONFIG).getPath();
        try (Reader reader = new FileReader(filePath)) {
            final Yaml yaml = new Yaml();
            final Map<String, Object> load = yaml.load(reader);
            if (ObjectUtil.isNotNull(load) && ObjectUtil.isNotNull(load.get(key))) {
                final DatabaseConnectionDto d = BeanUtil.toBean(load.get(key), DatabaseConnectionDto.class, CopyOptions.create().setIgnoreNullValue(true));
                try (Connection connection = DriverManager.getConnection(d.getUrl(), d.getDbUser(), d.getDbPassword())) {
                    final String scheme = d.getScheme();
                    if (ObjectUtil.isNotNull(databaseConnection(connection, scheme)) && databaseExists(connection, scheme)) {
                        return d;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static Boolean writeConfig(DatabaseConnectionDto databaseConnectionDto, String key) {
        final ClassPathResource classPathResource = new ClassPathResource(DBCONFIG);
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);
        if (classPathResource.exists()) {
            try (Reader reader = new FileReader(classPathResource.getFile()); Writer writer = new FileWriter(classPathResource.getFile())) {
                final Map<String, Object> load = yaml.load(reader);
                load.put(key, BeanUtil.beanToMap(databaseConnectionDto, false, true));
                yaml.dump(load, writer);
                return true;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            try (Writer writer = new FileWriter(classPathResource.getPath())) {
                final HashMap<String, Object> map = new HashMap<>();
                map.put(key, BeanUtil.beanToMap(databaseConnectionDto, false, true));
                yaml.dump(map, writer);
                return true;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }

    public static DatabaseConnection databaseConnection(Connection connection, String scheme) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("use `%1$s`;", scheme));
        ResultSet resultSet = statement.executeQuery(String.format(QUERYLIST, scheme));
        while (resultSet.next()) {
            // 封装查询结果为 DatabaseConnectionVO 对象
            return new DatabaseConnection(resultSet.getInt("id"), resultSet.getString("url"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("driver"), resultSet.getString("scheme"));
        }
        resultSet.close();
        statement.close();
        return null;
    }

    /**
     * 以行为单位读取文件，并将文件的每一行格式化到ArrayList中，常用于读面向行的格式化文件
     */
    private static ArrayList<String> readFileByLines(Reader readerFile) {
        ArrayList<String> listStr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(readerFile);) {
            String tempString = null;
            int flag = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
                if ("".equals(tempString.trim()) || tempString.startsWith("--")) {
                    continue;
                }
                if (";".equals(tempString.substring(tempString.length() - 1))) {
                    if (flag == 1) {
                        sb.append(tempString);
                        listStr.add(sb.toString());
                        sb.delete(0, sb.length());
                        flag = 0;
                    } else {
                        listStr.add(tempString);
                    }
                } else {
                    flag = 1;
                    sb.append(tempString);
                }
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (readerFile != null) {
                try {
                    readerFile.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        sb.setLength(0);
        return listStr;
    }

    public static void runSqlByReadFileContent(Connection connection, InputStream reader) {
        try (InputStreamReader inputStream = new InputStreamReader(reader, StandardCharsets.UTF_8)) {
            ArrayList<String> sqlStr = readFileByLines(inputStream);
            if (sqlStr.size() > 0) {
                int num = batchDate(connection, sqlStr);
                if (num > 0) {
                    System.out.println("执行成功");
                } else {
                    System.out.println("未有执行的SQL语句");
                }
            } else {
                System.out.println("没有需要执行的SQL语句");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
    }

    /**
     * @param sql 包含待执行的SQL语句的ArrayList集合
     * @return int 影响的函数
     * @方法描述：批量执行SQL语句
     */
    public static int batchDate(Connection connection, ArrayList<String> sql) {
        Statement st = null;
        try {
            st = connection.createStatement();
            for (String subsql : sql) {
                st.addBatch(subsql);
            }
            st.executeBatch();
            return 1;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return 0;
        } finally {
            try {
                sql.clear();
                connection.commit();
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
