package Objects;


import java.util.Random;

public class Community {
    private Location entrance;
    private Location exit;

    private boolean isVisited;  // 是否到访

    private double trashWeight;
    private double trashVolume;

    Community() {
        Random ran = new Random();
        isVisited = false;
        trashWeight = 2 * ran.nextGaussian() + 2;
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
}
