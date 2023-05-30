package bo;

import dao.Custom.CustomImpl.CustomerDAOImpl;
import dao.Custom.CustomImpl.ItemDAOImpl;
import dao.Custom.CustomImpl.OrderDAOImpl;
import dao.Custom.CustomImpl.OrderDetailsDAOImpl;
import dao.Custom.CustomerDAO;
import dao.Custom.ItemDAO;
import dao.Custom.OrderDAO;
import dao.Custom.OrderDetailsDAO;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl {
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.search(id);
    }


    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.search(code);
    }

    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.exist(code);
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.exist(id);
    }

    public String generateOrderID() throws SQLException, ClassNotFoundException {
        OrderDAO orderDAO = new OrderDAOImpl();
        return orderDAO.generateNewID();
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.getAll();
    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.getAll();
    }


    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails){
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            //Check order id already exist or not
            OrderDAO orderDAO = new OrderDAOImpl();
            boolean b1 = orderDAO.exist(orderId);
            /*if order id already exist*/
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);
            //Save the Order to the order table
            boolean b2 = orderDAO.add(new OrderDTO(orderId, orderDate, customerId));

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            // add data to the Order Details table
            OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) new OrderDetailsDAOImpl();
            for (OrderDetailDTO detail : orderDetails) {
                boolean b3 = orderDetailsDAO.add((OrderDetailsDAO) detail);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //update item
                ItemDAO itemDAO = new ItemDAOImpl();
                boolean b = itemDAO.update(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public ItemDTO findItem(String code) {
        try {
            PlaceOrderBOImpl purchaseOrderBO = new PlaceOrderBOImpl();
            return purchaseOrderBO.searchItem(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
