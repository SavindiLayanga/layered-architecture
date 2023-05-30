package dao.Custom;

import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO {
    ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean add(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    OrderDTO search(String id) throws SQLException, ClassNotFoundException;
}
