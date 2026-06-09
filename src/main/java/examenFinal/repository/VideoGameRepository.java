package examenFinal.repository;

import examenFinal.model.VideoGame;

import java.util.ArrayList;
import java.util.List;

public class VideoGameRepository {

    private List<VideoGame> videoGames;

    public VideoGameRepository() {
        videoGames = new ArrayList<>();
    }

    // CREATE
    public void save(VideoGame game) {
        videoGames.add(game);
    }

    // READ ALL
    public List<VideoGame> findAll() {
        return videoGames;
    }

    // READ BY TITLE
    public VideoGame findByTitle(String title) {

        for (VideoGame game : videoGames) {

            if (game.getTitle().equalsIgnoreCase(title)) {
                return game;
            }

        }

        return null;
    }

    // DELETE
    public boolean delete(String title) {

        VideoGame game = findByTitle(title);

        if (game != null) {
            videoGames.remove(game);
            return true;
        }

        return false;
    }

    // UPDATE
    public boolean update(String title, VideoGame newGame) {

        for (int i = 0; i < videoGames.size(); i++) {

            if (videoGames.get(i).getTitle()
                    .equalsIgnoreCase(title)) {

                videoGames.set(i, newGame);
                return true;
            }
        }

        return false;
    }
}
