package Objects;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final double longitude;
    private final double latitude;
    private boolean isVisited;
    private boolean down;
    private boolean up;

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isUp() {
        return up;
    }

    /**
     * 构造函数
     * @param x 经度
     * @param y 纬度
     */
    public Location(double x, double y) {
        this.longitude = x;
        this.latitude = y;
        if (y >= x) {
            down = true;
            up = false;
        } else {
            down = false;
            up = true;
        }
    }

    public double getLongitude() {
        double c = Math.sqrt(1);
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    /**
     * 计算两点距离
     * @param l1
     * @param l2
     * @return double 两点距离
     */
    public static double calDistance(Location l1, Location l2) {
        double deltaX = Math.pow(l1.longitude - l2.longitude, 2);
        double deltaY = Math.pow(l1.latitude - l2.latitude, 2);
        return Math.sqrt(deltaX + deltaY);
    }


    @Override
    public String toString() {
        return "[" +
                (int )longitude +
                ", " + (int) latitude +
                ']';
    }

}
