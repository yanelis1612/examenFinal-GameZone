package examenFinal.service;

import examenFinal.model.VideoGame;
import examenFinal.repository.VideoGameRepository;

import java.util.List;

public class VideoGameService {

    private final VideoGameRepository repository;

    public VideoGameService() {
        this.repository = new VideoGameRepository();
    }

    public void addVideoGame(VideoGame game) {
        if (game.getTitle() == null || game.getTitle().trim().isEmpty()) {
            throw new RuntimeException("El título es obligatorio");
        }

        if (game.getPrice() <= 0) {
            throw new RuntimeException("El precio debe ser mayor que 0");
        }

        if (game.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        if (repository.findByTitle(game.getTitle()) != null) {
            throw new RuntimeException("El videojuego ya existe");
        }

        repository.save(game);
    }

    public List<VideoGame> getAllGames() {
        return repository.findAll();
    }

    public VideoGame findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public boolean deleteGame(String title) {
        return repository.delete(title);
    }

    public boolean updateGame(String title, VideoGame newGame) {
        return repository.update(title, newGame);
    }

    public List<VideoGame> findByPlatform(String platform) {
        return repository.findByPlatform(platform);
    }
}
