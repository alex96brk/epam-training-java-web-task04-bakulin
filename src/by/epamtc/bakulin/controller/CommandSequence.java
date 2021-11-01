package by.epamtc.bakulin.controller;

import by.epamtc.bakulin.controller.command.Command;
import by.epamtc.bakulin.controller.command.CommandSequence;

import java.util.HashMap;
import java.util.Map;

public class CommandRepository {
    private Map<CommandSequence, Command> repository = new HashMap<>();
}
