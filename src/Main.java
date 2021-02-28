import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<String, String> resultMap(Path path) throws IOException {
        Map<String, String> result = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("K")) {
                String[] strings = line.split("=");
                String key = strings[0];
                String value = strings[1].replace(',', '.');
                double val = Double.parseDouble(value);
                switch (key) {
                    case "K1":
                        double minK1 = Double.parseDouble(View.list.get(0).getText());
                        double maxK1 = Double.parseDouble(View.list.get(1).getText());
                        if (val < minK1 || val > maxK1) {
                            result.put(key, value);
                        }
                        break;
                    case "K2":
                        double minK2 = Double.parseDouble(View.list.get(2).getText());
                        double maxK2 = Double.parseDouble(View.list.get(3).getText());
                        if (val < minK2 || val > maxK2) {
                            result.put(key, value);
                        }
                        break;
                    case "K3":
                        double minK3 = Double.parseDouble(View.list.get(4).getText());
                        double maxK3 = Double.parseDouble(View.list.get(5).getText());
                        if (val < minK3 || val > maxK3) {
                            result.put(key, value);
                        }
                        break;
                    case "K4":
                        double minK4 = Double.parseDouble(View.list.get(6).getText());
                        double maxK4 = Double.parseDouble(View.list.get(7).getText());
                        if (val < minK4 || val > maxK4) {
                            result.put(key, value);
                        }
                        break;
                    case "K5":
                        double minK5 = Double.parseDouble(View.list.get(8).getText());
                        double maxK5 = Double.parseDouble(View.list.get(9).getText());
                        if (val < minK5 || val > maxK5) {
                            result.put(key, value);
                        }
                        break;
                    case "K6":
                        double minK6 = Double.parseDouble(View.list.get(10).getText());
                        double maxK6 = Double.parseDouble(View.list.get(11).getText());
                        if (val < minK6 || val > maxK6) {
                            result.put(key, value);
                        }
                        break;
                    case "K7":
                        double minK7 = Double.parseDouble(View.list.get(12).getText());
                        double maxK7 = Double.parseDouble(View.list.get(13).getText());
                        if (val < minK7 || val > maxK7) {
                            result.put(key, value);
                        }
                        break;
                }
            }
        }
        reader.close();
        return result;
    }

    public static void isDirectory(Path path, String result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(result, true));
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            for (Path path1 : files) {
                if (Files.isRegularFile(path1)) {
                    Map<String, String> map = resultMap(path1);
                    if (map.size() > 0) {
                        View.textArea.append(path1.getFileName() + ":\n");
                        writer.write(path1.getFileName().toString().substring(0,path1.getFileName().toString().length() -4));
                        for (String s : map.keySet()) {
                            View.textArea.append(s + " = " + map.get(s) + "\n");
//                            writer.write(s + " = " + map.get(s) + "\n");
                        }
                        View.textArea.append("\n");
                        writer.write("\n");
                    }
                }
                if (Files.isDirectory(path1)) {
                    isDirectory(path1, result);
                }
            }
        }
        writer.close();
    }
}
