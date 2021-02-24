package com.nure.command;

import com.nure.command.admin.FindRoomsCommand;
import com.nure.command.admin.SetRoomForUserCommand;
import com.nure.command.admin.ShowOrdersCommand;
import com.nure.command.client.BookRoomCommand;
import com.nure.command.client.CancelOrderCommand;
import com.nure.command.client.CreateOrderCommand;
import com.nure.command.client.DownloadBillCommand;
import com.nure.command.client.EditUserCommand;
import com.nure.command.client.OpenPersonalAccountCommand;
import com.nure.command.client.PayCommand;
import com.nure.command.client.RegisterClientCommand;
import com.nure.command.common.ChangeLanguageCommand;
import com.nure.command.common.ErrorCommand;
import com.nure.command.common.LoginCommand;
import com.nure.command.common.LogoutCommand;
import com.nure.command.common.RoomDescriptionCommand;
import com.nure.command.common.ShowRoomsCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;
/**
 * Holder for all commands.
 *
 */
public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap();

    static {
        commands.put("login", new LoginCommand());
        commands.put("showRooms", new ShowRoomsCommand());
        commands.put("makeOrder", new CreateOrderCommand());
        commands.put("register", new RegisterClientCommand());
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
        commands.put("editUser", new EditUserCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("downloadFile", new DownloadBillCommand());

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
