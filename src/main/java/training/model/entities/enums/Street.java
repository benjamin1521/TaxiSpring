package training.model.entities.enums;

public enum Street {
    Bondarska(0),
    Gorkogo(1),
    Kashtanova(2),
    Zamkova(3),
    Tverska(4),
    Urlovska(5),
    Yaltinska(6),
    Lvivska(7),
    Parkova(8);

    int cordinate;

    Street(int cord) {
        cordinate = cord;
    }

    public int getCordinate() {
        return cordinate;
    }

}
