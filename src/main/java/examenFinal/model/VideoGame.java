package examenFinal.model;

public abstract class VideoGame {

    protected String title;
    protected String platform;
    protected double price;
    protected int stock;

    public VideoGame(String title,
                     String platform,
                     double price,
                     int stock) {

        this.title = title;
        this.platform = platform;
        this.price = price;
        this.stock = stock;
    }

    public abstract double calculateFinalPrice();
    public abstract String getType();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return title + " - " + platform;
    }
}
