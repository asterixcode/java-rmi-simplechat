package chatsystem.client.network;

import chatsystem.shared.networking.ClientInterface;
import chatsystem.shared.networking.ServerInterface;
import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements Client, ClientInterface {
  private ServerInterface server;
  private PropertyChangeSupport support;

  public RMIClient() {
    support = new PropertyChangeSupport(this);
  }

  @Override
  public void startClient(){
    try {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (ServerInterface) registry.lookup("RMIServer");
    }
    catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void login(String username) {
    try {
      server.registerClient(this, username);
      server.login(username);
    } catch (RemoteException e) {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public void sendMessage(Message message) {
    try {
      server.broadcast(message);
    } catch (RemoteException e) {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public void getUserList(String username) {
    try {
      server.getUserList(username);
    } catch (RemoteException e) {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public void disconnect(String username) {
    try {
      server.disconnect(username);
    } catch (RemoteException e) {
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public void broadcast(Message message) {
    support.firePropertyChange(UserAction.BROADCAST.toString(), null, message);
  }

  @Override
  public void userList(ArrayList<String> newValue) {
    support.firePropertyChange(UserAction.USER_LIST.toString(), null, newValue);
  }

  @Override
  public void addListener(String eventName, PropertyChangeListener listener) {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override
  public void removeListener(String eventName, PropertyChangeListener listener) {
    support.removePropertyChangeListener(eventName, listener);
  }
}
