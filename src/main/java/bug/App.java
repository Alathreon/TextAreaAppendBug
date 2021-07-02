package bug;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Scanner;

public class App extends Application {

    public static App instance;

    public App(){
        instance = this;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("View.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void redirectIO(InputStream src, TextArea ta){

        // NPE found in this thread
        new Thread(() -> {
            Scanner scanner = new Scanner(src);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine() + System.getProperty("line.separator"));
                if(sb.length() > 500){
                    ta.appendText(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }).start();

    }

    public static void main(String[] args){

        launch(args);

    }

}
