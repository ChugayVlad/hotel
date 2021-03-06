package com.nure.command.admin;

import com.nure.command.Command;
import com.nure.command.util.PathUtil;
import com.nure.entity.Order;
import com.nure.exception.AppException;
import com.nure.service.OrderService;
import com.nure.service.impl.OrderServiceImpl;
import com.nure.util.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * List of orders command.
 *
 */
public class ShowOrdersCommand implements Command {
    private static final Logger LOG = Logger.getLogger(ShowOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        PathUtil.saveCurrentPathToSession(request);
        HttpSession session = request.getSession();

        /*if (!Role.ADMIN.equals(session.getAttribute("userRole"))){
            throw new AppException("You dont have permissions!!!");
        }*/

        String paramPage = request.getParameter("page");
        String paramPageSize = request.getParameter("pageSize");
        int page = 1;
        if(paramPage != null && !paramPage.isEmpty()){
            page = Integer.parseInt(paramPage);
        }
        int pageSize = 5;
        if(paramPageSize != null && !paramPageSize.isEmpty()){
            pageSize = Integer.parseInt(paramPageSize);
        }

        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.getAllOrders(page, pageSize);
        LOG.trace("Orders --> " + orders);

        request.setAttribute("orders", orders);

        int roomsNumber = orderService.getOrdersCount();
        int maxPage = (int) Math.ceil((double)roomsNumber / pageSize);

        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("maxPage", maxPage);

        LOG.debug("Command finished");
        return Path.PAGE_ORDERS;
    }
}
