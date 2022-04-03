package Objects;

public class KitchenWaste extends TrashType{
    public KitchenWaste(double weight, double volume) {
        super(weight, volume);
    }

    @Override
    public double getTrashWeight() {
        return trashWeight;
    }

    @Override
    public double getTrashVolume() {
        return trashVolume;
    }
}
