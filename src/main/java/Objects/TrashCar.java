package Objects;

public class TrashCar {
    private final double MAX_WEIGHT;
    private final double MAX_VOLUME;
    private double currentWeight; // 单位为吨
    private double currentVolume; // 单位为立方米
    private double carWidth;
    private String carType;

    /**
     * 高德此分类依据国标。1：微型车，2：轻型车（默认值），3：中型车，4：重型车
     */
    private int carSize;
    private Location location;

    /**
     *
     * @param max_weight 垃圾车最大载重量
     * @param max_volume 垃圾车最大容积
     */
    public TrashCar(
            double max_weight,
            double max_volume,
            double width,
            Location initLocation,
            String carType) {
        MAX_WEIGHT = max_weight;
        MAX_VOLUME = max_volume;
        currentVolume = 0;
        currentWeight = 0;
        this.location = initLocation;
        carWidth = width;
        this.carType = carType;
    }

    public Location getLocation() {
        return this.location;
    }

    /**
     * 装载垃圾
     * @param weight 装载垃圾的重量
     * @param volume 装载垃圾的体积
     * @return 装载是否成功
     */
    public boolean loadTrash(double weight, double volume) {
        if (weight > getLeftWeight() || volume > getLeftVolume())
            return false;
        currentVolume += volume;
        currentWeight += weight;
        return true;
    }

    /**
     * 剩余可装载的垃圾重量
     * @return double weight
     */
    public double getLeftWeight() {
        return MAX_WEIGHT - currentWeight;
    }

    /**
     * 剩余可装载的垃圾容积
     * @return double volume
     */
    public double getLeftVolume() {
        return MAX_VOLUME - currentVolume;
    }
}
