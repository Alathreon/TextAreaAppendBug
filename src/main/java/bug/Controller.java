package bug;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextArea ta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            // Load batch file (loops to 10,000)
            URL res = this.getClass().getClassLoader().getResource("loop.bat");
            String path = res.toString().replaceFirst("file:/", "");
            System.out.println(path);
            // Start batch process
            Process p = new ProcessBuilder("cmd", "/c", path).start();
            // Redirect IO
            App.redirectIO(p.getInputStream(), ta);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
