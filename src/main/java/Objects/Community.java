package Objects;


import java.util.Random;

/**
 * 社区类
 */
public class Community {
    //入口出口，有可能相同
    private Location entrance;
    private Location exit;

    private boolean isVisited;  // 是否到访

    private double trashWeight; // 社区垃圾总量
    private double trashVolume; //社区垃圾总体积
    private double disToTrashCenter; //距离垃圾回收站距离

    public Community(Location entrance, Location exit) {
        isVisited = false;
        this.entrance = entrance;
        this.exit = exit;
        disToTrashCenter = Location.calDistance(this.entrance, new Location(0, 0));
    }

    public void setVisited() {
        this.isVisited = true;
    }

    public Location getEntrance() {
        return entrance;
    }

    public Location getExit() {
        return exit;
    }

    public double getTrashWeight() {
        return trashWeight;
    }

    public double getTrashVolume() {
        return trashVolume;
    }

    public void setEntrance(Location entrance) {
        this.entrance = entrance;
    }

    public void setExit(Location exit) {
        this.exit = exit;
    }

    public void setTrashWeight(double trashWeight) {
        this.trashWeight = trashWeight;
    }

    public void setTrashVolume(double trashVolume) {
        this.trashVolume = trashVolume;
    }

    @Override
    public String toString() {
        return "Community{" +
                "entrance=" + entrance +
                ", dis=" + disToTrashCenter +
                ", exit=" + exit +
                ", isVisited=" + isVisited +
                ", trashWeight=" + trashWeight +
                ", trashVolume=" + trashVolume +
                '}';
    }
}
