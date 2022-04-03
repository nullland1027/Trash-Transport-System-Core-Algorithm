package Objects;

public abstract class TrashType {
    public double trashWeight;
    public double trashVolume;

    public TrashType(double weight, double volume) {
        trashWeight = weight;
        trashVolume = volume;
    }

    public abstract double getTrashWeight();
    public abstract double getTrashVolume();
}
