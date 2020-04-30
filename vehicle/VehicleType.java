package vehicle;

public enum VehicleType {
    DRONE,
    BOAT,
    CHOPPER,
    PLANE,
    SPACESHIP;

    public static boolean contains(String type) {
        if (type == null || type.equals(""))
            return false;
        type = type.toUpperCase();
        return type.equals("DRONE") || type.equals("BOAT") || type.equals("CHOPPER") || type.equals("PLANE") || type.equals("SPACESHIP");
    }

    public static VehicleType getByName(String type) {
        type = type.toUpperCase();
        switch (type) {
            case "DRONE":
                return VehicleType.DRONE;
            case "BOAT":
                return VehicleType.BOAT;
            case "CHOPPER":
                return VehicleType.CHOPPER;
            case "PLANE":
                return VehicleType.PLANE;
            case "SPACESHIP":
                return VehicleType.SPACESHIP;
            default:
                return null;
        }
    }

    public static String vehicleTypeToString() {
        return "DRONE, BOAT, CHOPPER, PLANE, SPACESHIP";
    }
}
