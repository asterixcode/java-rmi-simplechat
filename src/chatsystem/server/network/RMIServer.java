package chatsystem.server.network;

import chatsystem.server.model.ConnectionPool;
import chatsystem.shared.networking.ClientInterface;
import chatsystem.shared.networking.ServerInterface;
import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.UserAction;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer implements ServerInterface {

  private ConnectionPool pool;
  private PropertyChangeListener broadcastListener;
  private PropertyChangeListener userListListener;

  public RMIServer(ConnectionPool pool) throws RemoteException {
    UnicastRemoteObject.exportObject(this, 0);
    this.pool = pool;
  }

  public void start() throws RemoteException, AlreadyBoundException {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("RMIServer", this);
  }

  @Override
  public void registerClient(ClientInterface client, String username) {
    broadcastListener = (event) -> {
      try {
        client.broadcast((Message) event.getNewValue());
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    };

    userListListener = (event) -> {
      try {
        client.userList((ArrayList<String>) event.getNewValue());
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    };

    pool.addListener(UserAction.BROADCAST.toString(), broadcastListener);
    pool.addListener(UserAction.USER_LIST.toString(), userListListener);
  }

  @Override
  public void login(String username) {
    pool.addUser(username);
  }

  @Override
  public void disconnect(String username) {
    pool.removeUser(username);
  }

  @Override
  public void broadcast(Message message) {
    pool.broadcast(message);
  }

  @Override
  public void getUserList(String username) {
    pool.updateUserList(username);
  }
}
