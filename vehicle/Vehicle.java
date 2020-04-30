package vehicle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Представление объекта com.vehicle.Vehicle
 */
@XmlRootElement(name = "com.vehicle.Vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle implements Comparable<Vehicle>, Serializable {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Double enginePower; //Поле может быть null, Значение поля должно быть больше 0

    private Integer fuelConsumption; //Поле может быть null, Значение поля должно быть больше 0

    private VehicleType type; //Поле не может быть null

    private FuelType fuelType; //Поле может быть null

    /**
     * Конструктор по умолчанию
     */
    public Vehicle() {
        coordinates = new Coordinates();
    }

    /**
     * Setter поля id
     *
     * @param id Устанавливаемое значение
     * @return Признак успешности выполнения
     */
    public boolean setId(String id) {
        if (id == null || id.length() == 0)
            return false;
        try {
            Long tempId = Long.parseLong(id);
            if (tempId <= 0)
                return false;
            this.id = tempId;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Окончательная инициализация объекта, для которого вызывется метод
     *
     * @return Ссылка на объект, для которого метод вызван
     */
    public Vehicle fullyInitialize() {
        id = Math.abs((long) hashCode());
        creationDate = LocalDate.now();
        return this;
    }

    /**
     * Setter поля name
     *
     * @param name Устанавливаемое значение
     * @return Признак успешности выполнения
     */
    public boolean setName(String name) {
        if (name == null || name.equals(""))
            return false;
        else this.name = name;
        return true;
    }

    /**
     * Setter поля enginePower
     *
     * @param enginePower Устанавливаемое значение
     * @return Признак успешности выполнения
     */
    public boolean setEnginePower(String enginePower) {
        if (enginePower == null || enginePower.equals("") || enginePower.equals("null"))
            return true;
        try {
            Double ep = Double.parseDouble(enginePower);
            if (ep <= 0)
                return false;
            this.enginePower = ep;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Setter поля fuelConsumption
     *
     * @param fuelConsumption Устанавливаемое значение
     * @return Признак успешности выполнения
     */
    public boolean setFuelConsumption(String fuelConsumption) {
        if (fuelConsumption == null || fuelConsumption.length() == 0 || fuelConsumption.equals("null"))
            return true;
        try {
            int fc = Integer.parseInt(fuelConsumption);
            if (fc <= 0)
                return false;
            this.fuelConsumption = fc;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Setter поля type
     *
     * @param type Устанавливаемое значение
     * @return Признак успешности выполнения
     */
    public boolean setType(String type) {
        if (type == null || type.equals(""))
            return false;
        type = type.toUpperCase();
        switch (type) {
            case "DRONE":
                this.type = VehicleType.DRONE;
                break;
            case "BOAT":
                this.type = VehicleType.BOAT;
                break;
            case "CHOPPER":
                this.type = VehicleType.CHOPPER;
                break;
            case "PLANE":
                this.type = VehicleType.PLANE;
                break;
            case "SPACESHIP":
                this.type = VehicleType.SPACESHIP;
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Setter поля fuelType
     *
     * @param fuelType Устанавливаемое значение
     * @return Признак успешности выполнения
     */
    public boolean setFuelType(String fuelType) {
        if (fuelType == null || fuelType.equals(""))
            return true;
        fuelType = fuelType.toUpperCase();
        switch (fuelType) {
            case "GASOLINE":
                this.fuelType = FuelType.GASOLINE;
                break;
            case "ELECTRICITY":
                this.fuelType = FuelType.ELECTRICITY;
                break;
            case "ALCOHOL":
                this.fuelType = FuelType.ALCOHOL;
                break;
            case "PLASMA":
                this.fuelType = FuelType.PLASMA;
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Получение строкового представления объекта
     *
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder(500);

        representation.append("\nНазвание: ");
        representation.append(name);
        representation.append("\nМестоположение: ");
        representation.append(coordinates.toString());
        if (enginePower != null)
            representation.append("\nМощность: " + enginePower.toString());
        if (fuelConsumption != null)
            representation.append("\nПотребление топлива: " + fuelConsumption.toString());
        representation.append("\nТип: ");
        representation.append(type.toString());
        if (fuelType != null)
            representation.append("\nИспользует топливо: " + fuelType.toString());
        representation.append("\nДата создания: ");
        representation.append(creationDate.toString());
        representation.append("\nid: ");
        representation.append(id.toString());
        representation.append('\n');
        return representation.toString();
    }

    /**
     * Getter поля id
     *
     * @return значение поля id объекта
     */
    public Long getId() {
        return id;
    }

    /**
     * Getter поля name
     *
     * @return значение поля name объекта
     */
    public String getName() {
        return name;
    }

    /**
     * Getter поля coordinates
     *
     * @return значение поля coordinates объекта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Setter поля coordinates
     *
     * @param coordinates Строковое представление устанавливаемого значения
     * @return Признак успешности выполнения
     */
    public boolean setCoordinates(String coordinates) {
        String[] coords = coordinates.split(" ");
        if (coords.length != 1) {
            return this.coordinates.setX(coords[0]) && this.coordinates.setY(coords[1]);
        }
        return false;
    }

    /**
     * Getter поля creationDate
     *
     * @return значение поля creationDate объекта
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Setter поля creationDate
     *
     * @param creationDate Строковое представление устанавливаемого значения
     * @return Признак успешности выполнения
     */
    protected boolean setCreationDate(String creationDate) {
        if (creationDate == null || creationDate.equals(""))
            return false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.ENGLISH);
        try {
            this.creationDate = LocalDate.parse(creationDate, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Getter поля enginePower
     *
     * @return значение поля enginePower объекта
     */
    public Double getEnginePower() {
        return enginePower;
    }

    /**
     * Getter поля fuelConsumption
     *
     * @return значение поля fuelConsumption объекта
     */
    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * Getter поля type
     *
     * @return значение поля type объекта
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Getter поля fuelType
     *
     * @return значение поля fuelType объекта
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * Получить hashCode объекта
     *
     * @return hashCode объекта
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if (fuelConsumption != null)
            result = prime * result + fuelConsumption;
        if (enginePower != null)
            result = prime * result + enginePower.intValue();
        result = prime * result + type.ordinal();
        if (fuelType != null)
            result = prime * result + fuelType.ordinal();
        result = prime * result + coordinates.hashCode();
        result = prime * result + name.hashCode();
        return result;
    }

    /**
     * Сравнение заданного объекта на равенство с объектом,
     * для которого осуществлён вызов
     *
     * @param o сравниваемый объект
     * @return true если равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        String thisString = this.toString().substring(0, this.toString().indexOf("id"));
        String vehicleString = this.toString().substring(0, vehicle.toString().indexOf("id"));

        return thisString.equals(vehicleString);
    }

    /**
     * Сравнение заданного объекта с объектом,
     * для которого осуществлён вызов
     *
     * @param v объект, с которым сравнивается текущий объект
     * @return Целое число: отрицательное, если текущий меньше заданного,
     * 0, если они равны, и положительное, если текущий больше заданного.
     */

    @Override
    public int compareTo(Vehicle v) {
        int result = 0;
        result = type.compareTo(v.type);
        if (result != 0)
            return result;
        if (enginePower != null && v.enginePower != null) {
            result = enginePower.compareTo(v.enginePower);
            if (result != 0)
                return result;
        } else {
            if (enginePower != null)
                return 1;
            if (v.enginePower != null)
                return -1;
        }
        if (fuelType != null && v.fuelType != null) {
            result = fuelType.compareTo(v.fuelType);
            if (result != 0)
                return result;
        } else {
            if (fuelType != null)
                return 1;
            if (v.fuelType != null)
                return -1;
        }
        if (fuelConsumption != null && v.fuelConsumption != null) {
            result = v.fuelConsumption.compareTo(fuelConsumption);
            if (result != 0)
                return result;
        } else {
            if (fuelConsumption != null)
                return -1;
            if (v.fuelConsumption != null)
                return 1;
        }
        return name.compareTo(v.name);
    }
}