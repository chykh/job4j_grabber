package ru.job4j.clone;

public class CloneByConstr {
    int x;
    int y;
    InnerObject io = new InnerObject();

    public CloneByConstr(int x,  int y) {
        this.x = x;
        this.y = y;
        io = new InnerObject();
        io.num = 0;
    }

    public CloneByConstr(CloneByConstr obj) {
        this(obj.getX(), obj.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
