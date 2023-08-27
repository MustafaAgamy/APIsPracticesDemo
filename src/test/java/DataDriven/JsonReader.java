package DataDriven;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
