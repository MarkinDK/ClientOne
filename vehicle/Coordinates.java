package vehicle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Класс, представляющий пару координат (X;Y)
 */
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Comparable<Coordinates>, Serializable {
    /**
     *Координата x.
     * Значение должно быть больше -17
     */
    private int x; //Значение поля должно быть больше -17
    /**
     *Координата y.
     */
    private Double y = null; //Поле не может быть null

    /**
     *Конструктор по умолчанию.
     */
    public Coordinates() {
    }

    /**
     *Установка значения координаты x
     * @param x Cтроковое представление устанавливаемого значения
     * @return Признак успешности установки значения
     */
    public boolean setX(String x) {
        if (x == null || x.length() == 0)
            return false;
        try {
            x = x.trim();
            int xx = Integer.parseInt(x);
            if (xx <= -17)
                return false;
            this.x = xx;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *Установка значения координаты y
     * @param y Cтроковое представление устанавливаемого значения
     * @return Признак успешности установки значения
     */
    public boolean setY(String y) {
        if (y == null || y.length() == 0|| y.equals(""))
            return false;
        try {
            this.y = Double.parseDouble(y);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *Возвращает строковое представление координат
     * @return Строковое представление координат
     */
    @Override
    public String toString() {
        return x + " " + y.toString();
    }

    /**
     *Возвращает целочисленное значение координаты x
     * @return Целочисленное значение координаты x
     */
    private int getX() {
        return x;
    }

    /**
     *Возвращает целочисленное значение координаты y
     * @return Целочисленное значение координаты y
     */
    private Double getY() {
        return y;
    }

    /**
     *Сравнивает заданные координаты и координаты объекта,
     * для которого был осуществлён вызов
     * @param o Сравниваемый объект
     * @return Отрицательное число, если заданный больше, 0 -
     * если заданный равен текущему, и положительное,
     * если заданный меньше текущего
     */
    @Override
    public int compareTo(Coordinates o) {
        return (int) (x * x + y * y - o.getX() * o.getX() - o.getY() * o.getY());
    }

    /**
     *Сравнивает на равенство текущий объект и заданый
     * @param o Заданный объект
     * @return true если равны, и false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return getX() == that.getX() &&
                getY().equals(that.getY());
    }

    /**
     *Возвращает hashcode объекта
     * @return Hashcode объекта
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = prime * result + y.intValue();
        return result;
    }
}
