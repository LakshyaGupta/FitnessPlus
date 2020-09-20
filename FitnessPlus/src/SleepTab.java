import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

public class SleepTab {
    private Tab tab;
    private ListView listView = new ListView();
    private ArrayList<Sleep> records = new ArrayList<>();

    public SleepTab(Tab tab) {
        this.tab = tab;
    }

    public void open() throws Exception {
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

        TextField txt_hours = new TextField();
        Label lbl_hours = new Label("Hours slept: ");
        txt_hours.setLayoutX(875);
        txt_hours.setLayoutY(180);
        txt_hours.setPrefSize(350,45);
        lbl_hours.setLayoutX(750);
        lbl_hours.setLayoutY(180);
        //txt_hours.setEditable(false);

        Button btn_submit = new Button("Submit");
        btn_submit.setLayoutX(950);
        btn_submit.setLayoutY(300);
        btn_submit.setPrefSize(150,25);

        btn_submit.setOnMouseClicked(event -> {
            try {
                addSleep(Integer.parseInt(txt_id.getText()),Double.parseDouble(txt_hours.getText()));
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sleep has been logged successfully!");
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
        group.getChildren().addAll(txt_hours,lbl_hours,txt_id,lbl_id,listView,btn_submit);
        tab.setContent(group);
    }

    public JSONArray getSleep() {
        try {

            URL url = new URL("http://868bbecfb9b1.ngrok.io/getSleep");
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

    public JSONArray addSleep(int id, double hours) {
        try {

            URL url = new URL("http://868bbecfb9b1.ngrok.io/addSleep?id="+id+"&hours="+hours);
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
            JSONArray obj = getSleep();
            for (int x = 0; x < obj.length(); x++) {
                Sleep record = new Sleep(Integer.parseInt(obj.getJSONObject(x).get("id").toString()), Integer.parseInt(obj.getJSONObject(x).get("hours").toString()));
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
