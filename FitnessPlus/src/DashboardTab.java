import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DashboardTab {
    private Tab tab;
    private ListView listView = new ListView();
    private ArrayList<Dashboard> records = new ArrayList<>();

    public DashboardTab(Tab tab) {
        this.tab = tab;
    }

    public void open() throws Exception {
        ListView listView = new ListView();
        listView.setLayoutX(0);
        listView.setLayoutY(0);
        listView.setPrefSize(350,650);

        TextField txt_id = new TextField();
        Label lbl_id = new Label("ID: ");
        txt_id.setLayoutX(875);
        txt_id.setLayoutY(120);
        txt_id.setPrefSize(350,45);
        lbl_id.setLayoutX(750);
        lbl_id.setLayoutY(120);
        txt_id.setEditable(false);

        TextField txt_food = new TextField();
        Label lbl_food = new Label("Average Food: ");
        txt_food.setLayoutX(875);
        txt_food.setLayoutY(180);
        txt_food.setPrefSize(350,45);
        lbl_food.setLayoutX(750);
        lbl_food.setLayoutY(180);
        txt_food.setEditable(false);

        TextField txt_sleep = new TextField();
        Label lbl_sleep = new Label("Average Sleep: ");
        txt_sleep.setLayoutX(875);
        txt_sleep.setLayoutY(240);
        txt_sleep.setPrefSize(350,45);
        lbl_sleep.setLayoutX(750);
        lbl_sleep.setLayoutY(240);
        txt_sleep.setEditable(false);

        TextField txt_exercise = new TextField();
        Label lbl_exercise = new Label("Average Exercise: ");
        txt_exercise.setLayoutX(875);
        txt_exercise.setLayoutY(300);
        txt_exercise.setPrefSize(350,45);
        lbl_exercise.setLayoutX(750);
        lbl_exercise.setLayoutY(300);
        txt_exercise.setEditable(false);

        TextField txt_diff_food = new TextField();
        Label lbl_diff_food = new Label("Additional Needed Calories: ");
        txt_diff_food.setLayoutX(875);
        txt_diff_food.setLayoutY(360);
        txt_diff_food.setPrefSize(350,45);
        lbl_diff_food.setLayoutX(700);
        lbl_diff_food.setLayoutY(360);
        txt_diff_food.setEditable(false);

        TextField txt_diff_sleep = new TextField();
        Label lbl_diff_sleep = new Label("Additional Needed Sleep: ");
        txt_diff_sleep.setLayoutX(875);
        txt_diff_sleep.setLayoutY(420);
        txt_diff_sleep.setPrefSize(350,45);
        lbl_diff_sleep.setLayoutX(700);
        lbl_diff_sleep.setLayoutY(420);
        txt_diff_sleep.setEditable(false);

        TextField txt_diff_exercise = new TextField();
        Label lbl_diff_exercise = new Label("Additional Needed Exercise: ");
        txt_diff_exercise.setLayoutX(875);
        txt_diff_exercise.setLayoutY(480);
        txt_diff_exercise.setPrefSize(350,45);
        lbl_diff_exercise.setLayoutX(700);
        lbl_diff_exercise.setLayoutY(480);
        txt_diff_exercise.setEditable(false);

        listView.setOnMouseClicked(event -> {
            try {
                txt_id.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getId() + "");
                txt_exercise.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getAvg_exercise() + "");
                txt_food.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getAvg_food() + "");
                txt_sleep.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getAvg_sleep() + "");
                txt_diff_exercise.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getDiff_exercise() + "");
                txt_diff_food.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getDiff_food() + "");
                txt_diff_sleep.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getDiff_sleep() + "");
            }
            catch(Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid click.");
                alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(E -> alert.hide());
                alert.show();
                delay.play();
            }
        });

        retrieveData();

        Group group = new Group();
        group.getChildren().addAll(txt_diff_exercise,lbl_diff_exercise,lbl_diff_food,txt_diff_food,lbl_diff_sleep,txt_diff_sleep,txt_exercise,lbl_exercise,lbl_sleep,lbl_food,txt_food,txt_id,lbl_id,listView);
        tab.setContent(group);
    }

    public JSONArray getDashboard() {
        try {

            URL url = new URL("http://868bbecfb9b1.ngrok.io/getDashboard");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                JSONArray obj = new JSONArray(output);
                return obj;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean retrieveData() {
        try {
            listView.getItems().clear();
            records.clear();
            JSONArray obj = getDashboard();
            for (int x = 0; x < obj.length(); x++) {
                Dashboard record = new Dashboard(Integer.parseInt(obj.getJSONObject(x).get("id").toString()), Double.parseDouble(obj.getJSONObject(x).get("avg_exercise").toString()),Double.parseDouble(obj.getJSONObject(x).get("avg_food").toString()),Double.parseDouble(obj.getJSONObject(x).get("avg_sleep").toString()),Double.parseDouble(obj.getJSONObject(x).get("diff_exercise").toString()),Double.parseDouble(obj.getJSONObject(x).get("diff_food").toString()),Double.parseDouble(obj.getJSONObject(x).get("diff_sleep").toString()));
                records.add(record);
                listView.getItems().add(record);
            }

            return true;
        } catch (Exception E) {
            System.out.println(E.getStackTrace()[0]);
            System.out.println(E.toString());
            return false;
        }
    }
}


