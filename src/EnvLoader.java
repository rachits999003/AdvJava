import java.io.*;
import java.util.*;

public class EnvLoader {

    public static Map<String, String> load(String path) {
        Map<String, String> env = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("=", 2);
                env.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load .env file", e);
        }

        return env;
    }
}