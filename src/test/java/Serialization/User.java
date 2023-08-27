package Serialization;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"support"})
@Setter
@Getter
public class User {

    Data data;
}
