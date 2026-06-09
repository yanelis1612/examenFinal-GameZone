package examenFinal.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import examenFinal.model.DigitalVideoGame;
import examenFinal.model.PhysicalVideoGame;
import examenFinal.model.VideoGame;

import java.util.ArrayList;
import java.util.List;

public class JsonAdapter {

    // Convierte una lista de objetos Java a un JsonArray organizado
    public static JsonArray serializeList(List<VideoGame> videoGames, Gson gson) {
        JsonArray jsonArray = new JsonArray();
        for (VideoGame game : videoGames) {
            JsonObject jsonObject = gson.toJsonTree(game).getAsJsonObject();
            // Guardamos la marca de texto para saber qué tipo de juego es al leer
            jsonObject.addProperty("gameType", game.getType());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    // Convierte un JsonArray de texto de vuelta a objetos reales de Java
    public static List<VideoGame> deserializeList(JsonArray jsonArray, Gson gson) {
        List<VideoGame> list = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            if (jsonObject.has("gameType")) {
                String type = jsonObject.get("gameType").getAsString();
                if ("Digital".equals(type)) {
                    list.add(gson.fromJson(jsonObject, DigitalVideoGame.class));
                } else if ("Físico".equals(type)) {
                    list.add(gson.fromJson(jsonObject, PhysicalVideoGame.class));
                }
            }
        }
        return list;
    }
}
