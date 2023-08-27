package Interfaces;


import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.DefaultValue;
import org.aeonbits.owner.ConfigFactory;


@Sources({"file:src/test/resources/Configurations.properties"})
public interface Configurations extends FrameProperties {

    private static void setProperty(String key, String value) {
        var updatedProps = new java.util.Properties();
        updatedProps.setProperty(key, value);
        Configurations configurations = ConfigFactory.create(Configurations.class, updatedProps);
        System.setProperty(key, value);
    }

    @Key("baseUrl")
    @DefaultValue("insertUrL")
    String baseUrl();

    @Key("jsonFileName")
    @DefaultValue("data.json")
    String jsonFileName();

     class setProperty implements FrameProperties.SetProperty {
         public void baseUrl(String value) {
             setProperty("baseUrl", value);
         }
         public void jsonFileName(String value) {
             setProperty("jsonFileName", value);
         }

     }

}