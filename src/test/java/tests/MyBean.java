package tests;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MyBean {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
}

