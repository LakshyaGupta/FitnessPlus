import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class Food {
    private Tab tab;

    public Food(Tab tab) {
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

        TextField txt_firstName = new TextField();
        Label lbl_firstName = new Label("First Name: ");
        txt_firstName.setLayoutX(875);
        txt_firstName.setLayoutY(180);
        txt_firstName.setPrefSize(350,45);
        lbl_firstName.setLayoutX(750);
        lbl_firstName.setLayoutY(180);
        txt_firstName.setEditable(false);

        TextField txt_lastName = new TextField();
        Label lbl_lastName = new Label("Last Name: ");
        txt_lastName.setLayoutX(875);
        txt_lastName.setLayoutY(240);
        txt_lastName.setPrefSize(350,45);
        lbl_lastName.setLayoutX(750);
        lbl_lastName.setLayoutY(240);
        txt_lastName.setEditable(false);

        Group group = new Group();
        group.getChildren().addAll(txt_firstName,lbl_firstName,txt_lastName,lbl_lastName,txt_id,lbl_id,listView);
        tab.setContent(group);
    }
}
