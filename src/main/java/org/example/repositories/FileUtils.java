package org.example.repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;

@Repository
public class FileUtils {

    /**
     * Read File and return JsonNode
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static JsonNode readJsonFromFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        URL res = FileUtils.class.getClassLoader().getResource(filePath);
        if (res == null) {
            throw new IllegalArgumentException(String.format("File not found! - %s", filePath));
        }
        return mapper.readTree(res);
    }
}
