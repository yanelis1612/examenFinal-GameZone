package examenFinal.repository;

import com.google.gson.*;
import examenFinal.model.VideoGame;
import examenFinal.utils.JsonAdapter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VideoGameRepository {
    private static final String FILE_PATH = "src/main/resources/json/catalog.json";
    private final Gson gson;

    public VideoGameRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveAll(List<VideoGame> videoGames) {
        File file = new File(FILE_PATH);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (FileWriter writer = new FileWriter(file)) {
            JsonArray jsonArray = JsonAdapter.serializeList(videoGames, gson);
            gson.toJson(jsonArray, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo JSON: " + e.getMessage());
        }
    }

    public List<VideoGame> findAll() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(file)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonNull() || !jsonElement.isJsonArray()) {
                return new ArrayList<>();
            }
            return JsonAdapter.deserializeList(jsonElement.getAsJsonArray(), gson);
        } catch (Exception e) {
            System.err.println("Error al leer archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public void save(VideoGame game) {
        List<VideoGame> games = findAll();
        games.add(game);
        saveAll(games);
    }

    public VideoGame findByTitle(String title) {
        List<VideoGame> games = findAll();
        for (VideoGame game : games) {
            if (game.getTitle().equalsIgnoreCase(title)) {
                return game;
            }
        }
        return null;
    }

    public List<VideoGame> findByPlatform(String platform) {
        List<VideoGame> games = findAll();
        List<VideoGame> result = new ArrayList<>();
        for (VideoGame game : games) {
            if (game.getPlatform().equalsIgnoreCase(platform)) {
                result.add(game);
            }
        }
        return result;
    }

    public boolean delete(String title) {
        List<VideoGame> games = findAll();
        VideoGame game = null;

        for (VideoGame g : games) {
            if (g.getTitle().equalsIgnoreCase(title)) {
                game = g;
                break;
            }
        }

        if (game != null) {
            games.remove(game);
            saveAll(games);
            return true;
        }
        return false;
    }

    public boolean update(String title, VideoGame newGame) {
        List<VideoGame> games = findAll();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getTitle().equalsIgnoreCase(title)) {
                games.set(i, newGame);
                saveAll(games);
                return true;
            }
        }
        return false;
    }
}
