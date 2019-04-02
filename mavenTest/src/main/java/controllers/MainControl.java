package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author Fatima
 */
public class MainControl implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* TODO */
    }

    @FXML
    public void newFile(ActionEvent event) {
        textArea.setText(null);
    }

    @FXML
    public void open(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV file", "*.csv"),
                new FileChooser.ExtensionFilter("JOBJ file", "*.jobj"));

        File file = fileChooser.showOpenDialog(null);

        // BARE HUSK Å HA MED FEILHÅNDTERING? ER DET NØDVENDIG HER?
        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Det skjedde en feil!:");
            alert.show();
        }

        assert file != null;
        Path filePath = file.toPath();
        models.TextDocument doku = new models.TextDocument(filePath);

        textArea.setText(doku.read());
    }

    @FXML
    public void save(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV file", "*.csv"),
                new FileChooser.ExtensionFilter("JOBJ file", "*.jobj"));
        fileChooser.setTitle("Save file");
        File file = fileChooser.showSaveDialog(null);

        Path filePath = file.toPath();
        models.TextDocument doku = new models.TextDocument(filePath);

        doku.write(textArea.getText());
    }

    @FXML
    public void exit(ActionEvent event) {

        Platform.exit();
    }

}

