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

public class ExerciseTab {
    private Tab tab;
    private ListView listView = new ListView();
    private ArrayList<Exercise> records = new ArrayList<>();

    public ExerciseTab(Tab tab) {
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
        //txt_id.setEditable(false);

        TextField txt_name = new TextField();
        Label lbl_name = new Label("Exercise Type: ");
        txt_name.setLayoutX(875);
        txt_name.setLayoutY(180);
        txt_name.setPrefSize(350,45);
        lbl_name.setLayoutX(750);
        lbl_name.setLayoutY(180);
        //txt_name.setEditable(false);

        TextField txt_hours = new TextField();
        Label lbl_hours = new Label("Hours of Exercise: ");
        txt_hours.setLayoutX(875);
        txt_hours.setLayoutY(240);
        txt_hours.setPrefSize(350,45);
        lbl_hours.setLayoutX(750);
        lbl_hours.setLayoutY(240);
        //txt_hours.setEditable(false);

        Button btn_submit = new Button("Submit");
        btn_submit.setLayoutX(950);
        btn_submit.setLayoutY(360);
        btn_submit.setPrefSize(150,25);

        btn_submit.setOnMouseClicked(event -> {
            try {
                addExercise(Integer.parseInt(txt_id.getText()),txt_name.getText(),Double.parseDouble(txt_hours.getText()));
                Alert alert = new Alert(Alert.AlertType.ERROR, "Exercise has been logged successfully!");
                alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(E -> alert.hide());
                alert.show();
                delay.play();
            }
            catch (Exception e) {
                System.out.println(e.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR, "Required field is empty or incorrect.");
                alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(E -> alert.hide());
                alert.show();
                delay.play();
            }
        });

        listView.setOnMouseClicked(event -> {
            try {
                txt_id.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getId() + "");
                txt_name.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getName() + "");
                txt_hours.setText(records.get(listView.getSelectionModel().getSelectedIndex()).getHours() + "");
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
        group.getChildren().addAll(txt_name,lbl_name,txt_hours,lbl_hours,txt_id,lbl_id,listView,btn_submit);
        tab.setContent(group);
    }

    public JSONArray getExercise() {
        try {

            URL url = new URL("http://868bbecfb9b1.ngrok.io/getExercise");
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

    public JSONArray addExercise(int id, String name, double hours) {
        try {

            URL url = new URL("http://868bbecfb9b1.ngrok.io/addExercise?id="+id+"&name="+name+"&hours="+hours);
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
            JSONArray obj = getExercise();
            for (int x = 0; x < obj.length(); x++) {
                Exercise record = new Exercise(Integer.parseInt(obj.getJSONObject(x).get("id").toString()), obj.getJSONObject(x).get("name").toString(), Integer.parseInt(obj.getJSONObject(x).get("calories").toString()));
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
