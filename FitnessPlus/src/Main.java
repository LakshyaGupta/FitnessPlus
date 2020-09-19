import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TabPane tp = new TabPane();
        Tab tab1 = new Tab("Dashboard");
        Tab tab2 = new Tab("Exercise");
        Tab tab3 = new Tab("Food");
        Tab tab4 = new Tab("Sleep");
        tp.getTabs().addAll(tab1,tab2,tab3,tab4);
        tp.prefHeightProperty().bind(primaryStage.heightProperty());
        tp.prefWidthProperty().bind(primaryStage.widthProperty());

        //String configPath = "/Users/lakshyagupta/IdeaProjects/FitnessPlus/src/config.properties";

        new Dashboard(tab1).open();
        new Exercise(tab2).open();
        new Food(tab3).open();
        new Sleep(tab4).open();


        Group group = new Group();
        Scene scene = new Scene(group,1920,1080);

        group.getChildren().addAll(tp);
        primaryStage.setTitle("FitnessPlus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}