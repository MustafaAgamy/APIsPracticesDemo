package DataDriven;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JsonReader {

    private final String jsonFilePath;

    public JsonReader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @SneakyThrows
    public String readJson(String key) {
        InputStream input = JsonReader.class.getClassLoader().getResourceAsStream(jsonFilePath);

        if(input == null){
            throw new RuntimeException("No Json File Found with this Name");
        }

        JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(input));

        if (jsonObject.get(key) == null) {
            throw new RuntimeException("Invalid Key Entered");
        }
        return (String) jsonObject.get(key);
    }

    public void writeJson(String key, String value, String fileName) {
        JSONObject jsonObject;

        // Read existing JSON data from the file
        try (FileReader fileReader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".json")) {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException | ParseException e) {
            // If the file doesn't exist or parsing fails, create a new JSON object
            jsonObject = new JSONObject();
        }

        // Add or update the key-value pair in the JSON object
        jsonObject.put(key, value);

        // Write the updated JSON back to the file
        try (FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".json")) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
