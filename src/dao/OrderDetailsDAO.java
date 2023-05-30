package dao;

import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO {
    ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean add(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    OrderDetailDTO search(String id) throws SQLException, ClassNotFoundException;
}
