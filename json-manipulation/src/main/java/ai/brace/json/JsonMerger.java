package ai.brace.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class JsonMerger {
    public void writeMergedJson(final JsonObject jsonObject1, final JsonObject jsonObject2) throws IOException {
        mergeJson(jsonObject1, jsonObject2);
        writeMergeJson(jsonObject1);
    }

    private void mergeJson(JsonObject jsonObject1, JsonObject jsonObject2) {
        final JsonArray textArray1 = jsonObject1.getAsJsonArray("textArray");
        final JsonArray textArray2 = jsonObject2.getAsJsonArray("textArray");

        final JsonPrimitive lastModified1 = jsonObject1.getAsJsonPrimitive("lastModified");
        final JsonPrimitive lastModified2 = jsonObject2.getAsJsonPrimitive("lastModified");
        final JsonArray mergedTextArray = new JsonArray();

        if (lastModified1.getAsLong() > lastModified2.getAsLong()) {
            mergedTextArray.addAll(textArray1);
            mergedTextArray.addAll(textArray2);
        } else {
            mergedTextArray.addAll(textArray2);
            mergedTextArray.addAll(textArray1);
        }

        jsonObject1.addProperty("uuid", UUID.randomUUID().toString());
        jsonObject1.addProperty("lastModified", getIsoFormattedDate());
        jsonObject1.add("textArray", mergedTextArray);
    }

    private void writeMergeJson(JsonObject jsonObject1) throws IOException {
        final Path path = JsonLoader.getPathForResource("output.json");
        final String json = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(jsonObject1);
        System.out.println("Writing to file:\n" + path.toString() +
        "\nwriting json:\n" + json);
        Files.writeString(path, json);
    }

    private String getIsoFormattedDate() {
        TimeZone time = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        dateFormat.setTimeZone(time);
        return dateFormat.format(new Date());
    }
}
