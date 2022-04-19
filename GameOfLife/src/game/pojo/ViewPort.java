package game.pojo;

import java.util.Objects;

public class ViewPort {
    private int startX, endX;
    private int startY, endY;

    public ViewPort(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    @Override
    public String toString() {
        return "ViewPort{" +
                "startX=" + startX +
                ", endX=" + endX +
                ", startY=" + startY +
                ", endY=" + endY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewPort viewPort = (ViewPort) o;
        return startX == viewPort.startX && endX == viewPort.endX && startY == viewPort.startY && endY == viewPort.endY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, endX, startY, endY);
    }
}
