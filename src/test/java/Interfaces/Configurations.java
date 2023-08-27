package Interfaces;


import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.DefaultValue;


@Sources({"file:src/test/resources/Configurations.properties"})
public interface Configurations extends FrameProperties {

    @Key("baseUrl")
    @DefaultValue("insertUrL")
    String baseUrl();

    @Key("jsonFileName")
    @DefaultValue("data.json")
    String jsonFileName();

}