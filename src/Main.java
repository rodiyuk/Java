import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static Map<String, String> resultMap(Path path) throws IOException {
        Map<String,String> result = new HashMap<>();
        List<String> lines = Files.readAllLines(path);
        for (String line : lines){
            if (line.startsWith("K1") || line.startsWith("K2") || line.startsWith("K3")){
                result.put(line.split("=")[0], line.split("=")[1]);
            }
        }
        return result;
    }

    public static void isDirectory(Path path, String result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(result, true));
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path path1 : files) {
                if (Files.isRegularFile(path1)){
                    Map<String, String> map = resultMap(path1);
                    View.textArea.append(path1.getFileName().toAbsolutePath() + ":\n");
                    writer.write(path1.toAbsolutePath() + ":\n");
                    for (String s : map.keySet()) {
                        View.textArea.append(s + " = " + map.get(s) + "\n");
                        writer.write(s + " = " + map.get(s) + "\n");
                    }
                    View.textArea.append("\n");
                    writer.write("\n");
                }
                if (Files.isDirectory(path1)){
                    isDirectory(path1, result);
                }
            }
        }
        writer.close();
    }
}
