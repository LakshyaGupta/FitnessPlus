package com.example.demo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RecordController {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/fitness_plus_db?characterEncoding=utf8&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT-5";
    private final String USER_NAME = "root";
    private final String PWD = "heyhey12";
    private static final Logger LOG = Logger.getLogger(RecordController.class.getName());

    @GetMapping("/getDashboard")
    public String getDashboard() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT * FROM fitness_plus_db.dashboard;";
                ResultSet r1 = st1.executeQuery(q1);

                JSONArray jsonArray = new JSONArray();

                while(r1.next()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", r1.getObject("id"));
                    jsonObject.put("avg_food", r1.getObject("avg_food"));
                    jsonObject.put("avg_sleep", r1.getObject("calories"));
                    jsonObject.put("avg_exercise", r1.getObject("avg_exercise"));
                    jsonObject.put("food_diff", r1.getObject("food_diff"));
                    jsonObject.put("sleep_diff", r1.getObject("sleep_diff"));
                    jsonObject.put("exercise_diff", r1.getObject("exercise_diff"));
                    jsonArray.put(jsonObject);
                }

                return jsonArray.toString();

            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );

                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.getStackTrace()[0].getLineNumber() ); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );

            return null;
        }
    }

    @GetMapping("/getExercise")
    public String getExercise() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT * FROM fitness_plus_db.exercise;";
                ResultSet r1 = st1.executeQuery(q1);

                JSONArray jsonArray = new JSONArray();

                while(r1.next()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", r1.getObject("id"));
                    jsonObject.put("name", r1.getObject("name"));
                    jsonObject.put("hours", r1.getObject("hours"));
                    jsonArray.put(jsonObject);
                }

                return jsonArray.toString();

            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );

                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.getStackTrace()[0].getLineNumber() ); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );

            return null;
        }
    }

    @GetMapping("/getFood")
    public String getFood() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT * FROM fitness_plus_db.food;";
                ResultSet r1 = st1.executeQuery(q1);

                JSONArray jsonArray = new JSONArray();

                while(r1.next()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", r1.getObject("id"));
                    jsonObject.put("name", r1.getObject("name"));
                    jsonObject.put("calories", r1.getObject("calories"));
                    jsonArray.put(jsonObject);
                }

                return jsonArray.toString();

            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );

                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.getStackTrace()[0].getLineNumber() ); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );

            return null;
        }
    }



    @GetMapping("/getSleep")
    public String getSleep() {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT * FROM fitness_plus_db.sleep;";
                ResultSet r1 = st1.executeQuery(q1);

                JSONArray jsonArray = new JSONArray();

                while(r1.next()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", r1.getObject("id"));
                    jsonObject.put("hours", r1.getObject("hours"));
                    jsonArray.put(jsonObject);
                }

                return jsonArray.toString();

            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );

                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.getStackTrace()[0].getLineNumber() ); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );

            return null;
        }
    }


    @GetMapping("/getUser")
    public String getUser(int id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);
            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT * FROM fitness_plus_db.user WHERE id = " + id + ";";
                ResultSet r1 = st1.executeQuery(q1);

                JSONObject jsonObject = new JSONObject();
                if(r1.next()) {
                    jsonObject.put("first_name", r1.getObject("first_name"));
                    jsonObject.put("last_name", r1.getObject("last_name"));
                }

                return jsonObject.toString();

            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.getStackTrace()[0].getLineNumber() ); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

    @GetMapping("/getUserRecords")
    public String getUserRecords(int id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st = con.createStatement();
                String query = "SELECT * FROM fitness_plus_db.food,fitness_plus_db.exercise,fitness_plus_db.sleep WHERE id = " + id + ";";
                ResultSet r1 = st.executeQuery(query);

                JSONArray jsonArray = new JSONArray();
                while (r1.next()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", r1.getObject("first_name"));
                    jsonObject.put("sleep_hours", r1.getObject("sleep.hours"));
                    jsonObject.put("exercise_name", r1.getObject("exercise.name"));
                    jsonObject.put("exercise_hours", r1.getObject("exercise.hours"));
                    jsonObject.put("food_name", r1.getObject("food.name"));
                    jsonObject.put("calories", r1.getObject("calories"));
                    jsonArray.put(jsonObject);
                }

                return jsonArray.toString();

            }
            catch (Exception E) {
                System.out.println(E.toString());
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println(e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            return null;
        }
    }

    @GetMapping("/getUserDashboard")
    public String getUserDashboard(int id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT * FROM fitness_plus_db.dashboard WHERE id = " + id + ";";
                ResultSet r1 = st1.executeQuery(q1);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", r1.getObject("id"));
                jsonObject.put("avg_food", r1.getObject("avg_food"));
                jsonObject.put("avg_sleep", r1.getObject("calories"));
                jsonObject.put("avg_exercise", r1.getObject("avg_exercise"));
                jsonObject.put("food_diff", r1.getObject("food_diff"));
                jsonObject.put("sleep_diff", r1.getObject("sleep_diff"));
                jsonObject.put("exercise_diff", r1.getObject("exercise_diff"));

                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.getStackTrace()[0].getLineNumber() ); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

    @GetMapping("/getId")
    public String getId(String email) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT id AS rowcount1 FROM fitness_plus_db.user WHERE email = '" + email + "'";
                ResultSet r1 = st1.executeQuery(q1);
                int rowCount1 = -1;
                if(r1.next()) {
                    rowCount1 = r1.getInt("rowcount1");
                }

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rowCount1);
                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

    @GetMapping("/addSleep")
    public String addSleep(int id, double hours) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "INSERT INTO fitness_plus_db.sleep VALUES (" + id + ", " + hours + ");";
                st1.executeQuery(q1);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("output", true);
                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

    @GetMapping("/addFood")
    public String addFood(int id, String name, int calories) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "INSERT INTO fitness_plus_db.food VALUES (" + id + ", '" + name + "', " + calories + ");";
                st1.executeQuery(q1);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("output", true);
                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

    @GetMapping("/addExercise")
    public String addExercise(int id, String name, double hours) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "INSERT INTO fitness_plus_db.exercise VALUES (" + id + ", '" + name + "', " + hours + ");";
                st1.executeQuery(q1);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("output", true);
                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

    @GetMapping("/signUp")
    public String signUp(String first_name, String last_name, String email, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT COUNT(*) AS rowcount1 FROM fitness_plus_db.user WHERE email = '" + email + "'";
                ResultSet r1 = st1.executeQuery(q1);
                int rowCount1 = -1;
                if(r1.next()) {
                    rowCount1 = r1.getInt("rowcount1");
                }
                JSONObject jsonObject = new JSONObject();
                if(rowCount1 == 0) {
                    int id = -1;
                    String q2 = "SELECT MAX(id) AS rowcount2 FROM food_plus_db.user;";
                    r1 = st1.executeQuery(q2);
                    if (r1.next()) {
                        id = r1.getInt("rowcount2") + 1;
                        String q3 = "INSERT INTO food_plus_db.user VALUES (" + id + ", '" + first_name + "', '" + last_name + "', '" + email + "', '" + password + "');";
                        st1.executeUpdate(q3);
                        jsonObject.put("output", true);
                    }
                }
                else {
                    jsonObject.put("output", false);
                }
                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            return null;
        }
    }

    @GetMapping("/userExists")
    public String userExists(int id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PWD);

            try {
                Statement st1 = con.createStatement();
                String q1 = "SELECT COUNT(*) AS rowcount1 FROM fitness_plus_db.user WHERE email = " + id + ";";
                ResultSet r1 = st1.executeQuery(q1);
                int rowCount1 = -1;
                if(r1.next()) {
                    rowCount1 = r1.getInt("rowcount1");
                }

                JSONObject jsonObject = new JSONObject();
                if(rowCount1 == 0) {
                    jsonObject.put("output", false);
                }
                else {
                    jsonObject.put("output", true);
                }
                return jsonObject.toString();
            }
            catch (Exception E) {
                System.out.println(E.toString());
                System.out.println( E.getStackTrace()[0].getLineNumber() );
                return null;
            }
            finally {
                try {
                    con.close();
                } catch (Exception e) { System.out.println( e.toString()); }
            }
        }
        catch (Exception E) {
            System.out.println(E.toString());
            System.out.println( E.getStackTrace()[0].getLineNumber() );
            return null;
        }
    }

}