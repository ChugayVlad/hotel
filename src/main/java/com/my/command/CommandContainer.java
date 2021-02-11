package com.my.command;

import com.my.command.admin.FindRoomsCommand;
import com.my.command.admin.SetRoomForUserCommand;
import com.my.command.admin.ShowOrdersCommand;
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
        commands.put("register", new RegisterClientCommand());
        commands.put("successOrder", new SuccessOrderCommand()); //<<
        commands.put("roomDescription", new RoomDescriptionCommand());
        commands.put("openPersonalAccount", new OpenPersonalAccountCommand());
        commands.put("bookRoom", new BookRoomCommand());
        commands.put("error", new ErrorCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("showOrders", new ShowOrdersCommand());
        commands.put("findRoomsForUser", new FindRoomsCommand());
        commands.put("setRoom", new SetRoomForUserCommand());
        commands.put("pay", new PayCommand());
        commands.put("cancelOrder", new CancelOrderCommand());

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
