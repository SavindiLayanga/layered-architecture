package dao;

import model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(T dto) throws SQLException, ClassNotFoundException;
    public boolean update(T dto) throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public T search(String id) throws SQLException, ClassNotFoundException;

    interface OrderDetailsDAO {
        ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException;

        boolean add(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

        boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;

        boolean exist(String id) throws SQLException, ClassNotFoundException;

        String generateNewID() throws SQLException, ClassNotFoundException;

        boolean delete(String id) throws SQLException, ClassNotFoundException;

        OrderDetailDTO search(String id) throws SQLException, ClassNotFoundException;
    }
}
