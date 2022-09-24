package sweeper;

public enum Box {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,

    OPENED,
    CLOSED,
    FLAGGED,
    BOMBED,
    NOBOMB;

    public Object image;

    Box nextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }

    int getNumber() {
        int number = ordinal();
        if (number >= Box.NUM1.ordinal() && number <= Box.NUM8.ordinal()) {
            return number;
        }
        return -1;
    }
}
