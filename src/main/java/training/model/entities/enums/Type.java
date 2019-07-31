package training.model.entities.enums;

public enum Type {
    Eco(1, 100),
    Standart(2, 200),
    Comfort(3, 300),
    Premium(4, 400);

    int speed;
    int price;

    Type(int speed, int price) {
        this.speed = speed;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getSpeed() {
        return speed;
    }
}
