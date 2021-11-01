package by.epamtc.bakulin.controller;

import by.epamtc.bakulin.controller.command.Command;

public class Controller {

    private static final CommandSequence commandSequence = new CommandSequence();

    public static CommandSequence getCommandSequence() {
        return commandSequence;
    }

    public String runCommand(String cmdRequest) {

        Command command = commandSequence.getCommand(cmdRequest);
        return command.execute();
    }


}
