import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by 123 on 23.07.2017.
 */
public class StartParser {
    public static void main(String[] args) throws IOException {

        String source = args[0];
        InputStream inputStream = new FileInputStream(source);
        System.out.println("Enter  absolute path to result file:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        Path path = Paths.get(s);

        Path parent = path.getParent();
        Path root = path.getRoot();
        if (!parent.equals(root)) {
            Files.createDirectories(parent);
            Files.createFile(path);
        } else {
            Files.createFile(path);
        }
        File file = new File(path.toString());
        XMLParser parser = new XMLParser();
        parser.parse(inputStream);
        parser.convertObjectToXml(file);


    }
}
