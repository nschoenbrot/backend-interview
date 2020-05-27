package ai.brace.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class JsonLoader {

    // TODO avoid code duplication with other module.
    public static Path getPathForResource(final String path) {
        try {
            return Paths.get(ClassLoader.getSystemResource(path).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Integer, String> getIdToTextMap(final String fileName) throws IOException {
        final JsonObject jsonObject = getJsonObject(fileName);
        final JsonArray textArray = jsonObject.getAsJsonArray("textArray");

        final Map<Integer, String> idTextMap = new HashMap<>();
        for (JsonElement element : textArray) {
            final JsonObject textObject = element.getAsJsonObject();
            idTextMap.put(
                    textObject.get("id").getAsInt(),
                    textObject.get("textdata").getAsString()
            );
        }

        return idTextMap;
    }

    public JsonObject getJsonObject(final String fileName) throws IOException {
        final Stream<String> lines = Files.lines(getPathForResource(fileName));
        final StringBuilder json = new StringBuilder();
        lines.forEach(json::append);
        final JsonElement jsonElement = new JsonParser().parse(json.toString());
        return jsonElement.getAsJsonObject();
    }
}
