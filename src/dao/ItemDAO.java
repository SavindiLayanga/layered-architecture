package dao;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String code) throws SQLException, ClassNotFoundException;

    boolean add(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean exist(String code) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    ItemDTO search(String code) throws SQLException, ClassNotFoundException;
}
