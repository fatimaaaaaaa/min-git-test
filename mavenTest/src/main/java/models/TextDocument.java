package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class TextDocument {

    private Path path;

    public TextDocument(Path path) {
        this.path = path;

    }

    public void write(String text) throws IOException {

        File file = new File(text);
        BufferedWriter bw = null;
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.close();

            }
        }

    }

    public String read() throws IOException {


        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("" +path));

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return " " + path;
    }
}
