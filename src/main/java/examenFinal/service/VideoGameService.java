package examenFinal.service;

import examenFinal.model.VideoGame;
import examenFinal.repository.VideoGameRepository;

import java.util.List;

public class VideoGameService {

    private VideoGameRepository repository;

    public VideoGameService() {
        repository = new VideoGameRepository();
    }

    // CREATE
    public void addVideoGame(VideoGame game) {

        if (game.getTitle() == null ||
                game.getTitle().trim().isEmpty()) {

            throw new RuntimeException(
                    "El título es obligatorio");
        }

        if (game.getPrice() <= 0) {

            throw new RuntimeException(
                    "El precio debe ser mayor que 0");
        }

        if (game.getStock() < 0) {

            throw new RuntimeException(
                    "El stock no puede ser negativo");
        }

        if (repository.findByTitle(
                game.getTitle()) != null) {

            throw new RuntimeException(
                    "El videojuego ya existe");
        }

        repository.save(game);
    }

    // READ
    public List<VideoGame> getAllGames() {
        return repository.findAll();
    }

    public VideoGame findByTitle(String title) {
        return repository.findByTitle(title);
    }

    // DELETE
    public boolean deleteGame(String title) {
        return repository.delete(title);
    }

    // UPDATE
    public boolean updateGame(String title,
                              VideoGame newGame) {

        return repository.update(title, newGame);
    }
}
