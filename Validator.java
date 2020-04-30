import instruction.Instruction;
import vehicle.FuelType;
import vehicle.VehicleType;

import java.io.IOException;

public class Validator {
    private String argumentType;
    private String nameOfCommand;
    private String isCreative;

    public void validateName(Connector connector, String name) throws NoSuchCommandException, IOException {
        connector.send(new Instruction<>("validateRequest", name));
        String[] commandInfo = connector.receiveResult().split(" ");
        if (commandInfo.length != 3) throw new IOException();
        if (commandInfo[0].equals("Нет такой команды"))
            throw new NoSuchCommandException();
        nameOfCommand = commandInfo[0];
        argumentType = commandInfo[1];
        isCreative = commandInfo[2];
        //System.out.println("Имя подтверждено");
    }

    public void validateArgument(String argument) throws WrongArgumentException {
        if (argumentType.equals(argument))
            return;
        if (argumentType.equals("FuelType")) {
            if (!FuelType.contains(argument)) throw new WrongArgumentException();
        }
        if (argumentType.equals("VehicleType")) {
            if (!VehicleType.contains(argument)) throw new WrongArgumentException();
        }
        if (argumentType.equals("long")) {
            try {
                long id = Long.parseLong(argument);
                if (id <= 0) throw new WrongArgumentException();
            } catch (NumberFormatException ignored) {
                throw new WrongArgumentException();
            }
        }
        //System.out.println("Аргумент подтверждён");
    }

    public boolean isCreative() {
        return isCreative.equals("true");
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }

    public String getArgumentType() {
        return argumentType;
    }

    public boolean isPaired(){
        return isCreative()&!(argumentType.equals("none")||argumentType.equals("Vehicle"));
    }
}
