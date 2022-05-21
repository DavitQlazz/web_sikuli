package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SomeClass {

    @Test
    public void asd() throws JsonProcessingException {

        String json = "{\"id\":1,\"name\":\"My bean\"}";

        MyBean bean = new ObjectMapper()
                .readerFor(MyBean.class)
                .readValue(json);
        assertEquals(bean, "My bean");
        assertEquals(bean, 8);

    }
}
