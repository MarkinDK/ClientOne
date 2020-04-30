import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.SocketException;

import instruction.Instruction;
import javafx.util.Pair;
import vehicle.*;

public class Main {

    public static void main(String[] args) {
        Connector connector;
        try {
            connector = new Connector(InetAddress.getLocalHost(), 5000);
            Validator validator = new Validator();
            String argument;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while (true) {
                connector.connect();
                if (connector.isConnected()) {
                    System.out.println("Соединение установлено");
                    System.out.println("Введите команду");
                }
                if (connector.isConnected()) {
                    while (!(line = reader.readLine()).equals("exit")) {
                        if (line == null || line.equals(""))
                            continue;
                        argument = "none";
                        String[] data = line.split(" ", 2);
                        if (data.length != 1)
                            argument = data[1];
                        try {
                            validator.validateName(connector, data[0]);
                            if (validator.isPaired()) {
                                //System.out.println("Требуется пара");
                                Vehicle v = createNewVehicle();
                                Pair<String, Vehicle> pair = new Pair<>(argument, v);
                                connector.send(new Instruction<>(validator.getNameOfCommand(), pair));
                            } else if (validator.getArgumentType().equalsIgnoreCase("file")) {
                                File file = new File(argument);
                                if (file.exists()) {
                                    argument = FileCompounder.compoundFile(argument);
                                    try {
                                        connector.send(new Instruction<>(validator.getNameOfCommand(), FileCompounder.getBytes(argument)));
                                    } catch (IOException e) {
                                        //e.printStackTrace();
                                        System.out.println("Файл не существует");
                                    }
                                } else System.out.println("Файл не существует");

                            } else if (validator.isCreative()) {
                                System.out.println("Требуется Vehicle");
                                Vehicle v = createNewVehicle();
                                connector.send(new Instruction<>(validator.getNameOfCommand(), v));
                            } else {
                                //System.out.println("Обошлись без пары и Vehicle");
                                validator.validateArgument(argument);
                                connector.send(new Instruction<>(validator.getNameOfCommand(), argument));
                            }
                            System.out.println(connector.receiveResult());
                            System.out.println("Введите команду");
                        } catch (NoSuchCommandException e) {
                            System.out.println("Нет такой команды");
                        } catch (WrongArgumentException e) {
                            System.out.println("Неправильный аргумент команды");
                        } catch (SocketException e) {//is necessary?
                            //e.printStackTrace();
                        } catch (IOException e) {
                            //System.out.println("This exception if disconnected");
                            //e.printStackTrace();
                            break;
                        }
                    }
                } else {
                    System.out.println("Не удалось установить соединение или соединение разорвано, " +
                            "введите \"exit\" для выхода или \"again\" для попытки соединения ");
                    while (!(line = reader.readLine()).equals("exit"))
                        if (line.equals("again"))
                            break;
                    if (line.equals("exit")) {
                        connector.disconnect();
                        System.exit(0);
                    }
                }
            }
        } catch (IOException e) {
            //System.out.println("This exception if disconnected in the very end");
            //e.printStackTrace();
        }
    }


    private static Vehicle createNewVehicle() {
        Vehicle v = new Vehicle();
        boolean flag;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            do {
                System.out.println("Введите имя вашего корыта");
                flag = v.setName(reader.readLine());
            }
            while (!flag);
            Coordinates coordinates = new Coordinates();
            do {
                System.out.println("Введите координату X больше -17");
                flag = coordinates.setX(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите координату Y");
                flag = coordinates.setY(reader.readLine());
            }
            while (!flag);
            v.setCoordinates(coordinates.toString());
            do {
                System.out.println("Введите мощность двигателя");
                flag = v.setEnginePower(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите потребление топлива");
                flag = v.setFuelConsumption(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите тип аппарата:\n" + VehicleType.vehicleTypeToString());
                flag = v.setType(reader.readLine());
            }
            while (!flag);
            do {
                System.out.println("Введите тип топлива\n" + FuelType.fuelTypeEoString());
                flag = v.setFuelType(reader.readLine());
            }
            while (!flag);
        } catch (IOException ignored) {
        }
        return v;
    }
}
