package tools;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapper {
    private static ObjectMapper mapper;

    public static ObjectMapper getMapper() {
        if(mapper == null){
            mapper = new ObjectMapper();
        }
        return mapper;
    }
}


