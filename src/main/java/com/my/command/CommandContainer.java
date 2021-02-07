package com.my.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap();

    static {
        commands.put("login", new LoginCommand());
        commands.put("showRooms", new ShowRoomsCommand());
        commands.put("makeOrder", new CreateOrderCommand());
        commands.put("open_registration", new OpenRegistrationCommand());
        commands.put("register", new RegisterClientCommand());
        commands.put("successOrder", new SuccessOrderCommand());
        commands.put("roomDescription", new RoomDescriptionCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
