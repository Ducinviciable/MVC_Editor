package connect.NOTEPAD;

import java.io.*;
import java.nio.file.Files;
// import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextModel {
    private String textContent;

    public void openFile(File file) {
        try (Stream<String> stream = Files.lines(file.toPath())) {
            textContent = stream.collect(Collectors.joining("\n"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveFile(File file, String text) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath())) {
            writer.write(text);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
