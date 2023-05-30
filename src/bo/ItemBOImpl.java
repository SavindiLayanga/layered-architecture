package bo;

import dao.Custom.CustomImpl.ItemDAOImpl;
import dao.Custom.ItemDAO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO{
    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        //ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.getAll();
    }

    @Override
    public boolean add(ItemDTO dto) throws SQLException, ClassNotFoundException {
        //ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.add(dto);
    }

    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        //ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.update(dto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        //ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        //ItemDAO itemDAO = new ItemDAOImpl();
        return itemDAO.delete(id);
    }

    @Override
    public ItemDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
