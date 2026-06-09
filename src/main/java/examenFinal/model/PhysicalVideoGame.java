package examenFinal.model;

public class PhysicalVideoGame extends VideoGame {

    private boolean used;

    public PhysicalVideoGame(String title,
                             String platform,
                             double price,
                             int stock,
                             boolean used) {

        super(title, platform, price, stock);
        this.used = used;
    }

    @Override
    public double calculateFinalPrice() {

        if (used) {
            return price * 0.75;
        }

        return price;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    @Override
    public String getType() {
        return "Físico";
    }

    @Override
    public String toString() {
        return "PhysicalVideoGame{" +
                "title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", used=" + used +
                '}';
    }
}
