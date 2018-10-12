package main.java.fr.mickael.util;

public enum Color {

    RED(0),
    YELLOW(1),
    BLUE(2),
    ORANGE(3),
    GREEN(4),
    WHITE(5),
    PURPLE(6),
    BLACK(7),
    GREY(8),
    PINK(9);

    private int value;

    private Color(int value) {
        this.value = value;
    }

    public String toString() {
        return "" + value;
    }
}
