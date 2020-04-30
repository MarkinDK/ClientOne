package vehicle;

public enum FuelType {
    GASOLINE,
    ELECTRICITY,
    ALCOHOL,
    PLASMA;

    public static boolean contains(String fuelType) {
        if (fuelType == null || fuelType.equals(""))
            return false;
        fuelType = fuelType.toUpperCase();
        return fuelType.equals("GASOLINE")||fuelType.equals("ELECTRICITY")||fuelType.equals("ALCOHOL")||fuelType.equals("PLASMA");
    }

    public static FuelType getByName(String fuelType) {
        fuelType = fuelType.toUpperCase();
        switch (fuelType) {
            case "GASOLINE":
                return FuelType.GASOLINE;
            case "ELECTRICITY":
                return FuelType.ELECTRICITY;
            case "ALCOHOL":
                return FuelType.ALCOHOL;
            case "PLASMA":
                return FuelType.PLASMA;
            default:
                return null;
        }
    }

    public static String fuelTypeEoString() {
        return "GASOLINE, ELECTRICITY, ALCOHOL, PLASMA";
    }
}
