package dk.via.login.server;

import dk.via.login.dao.UserDao;
import dk.via.login.shared.User;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class DaoStorageAdapter implements UserStorage {
    private final UserDao dao;

    public DaoStorageAdapter(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public User getUser(String username) throws RemoteException {
        try {
            return dao.read(username);
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }

    @Override
    public void storeUser(User user) throws RemoteException {
        try {
            if (dao.read(user.getUserName()) == null) {
                dao.create(user.getUserName(), user.getPassword(), user.getName());
            } else {
                dao.update(user);
            }
        } catch (SQLException e) {
            throw new RemoteException(e.getMessage(), e);
        }
    }
}
