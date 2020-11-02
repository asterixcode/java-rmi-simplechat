package chatsystem.server.model;

import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.Subject;
import chatsystem.shared.util.UserAction;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ConnectionPool implements Subject
{
  private PropertyChangeSupport support;
  private ArrayList<String> userList;

  public ConnectionPool() {
    support = new PropertyChangeSupport(this);
    userList = new ArrayList<>();
  }

  public void addUser(String username) {
    userList.add(username);
    getUsersList();
  }

  public void removeUser(String username) {
    userList.remove(username);
    getUsersList();
  }

  public void broadcast(Message message) {
    support.firePropertyChange(UserAction.BROADCAST.toString(), null, message);
  }

  public void getUsersList() {
    support.firePropertyChange(UserAction.USER_LIST.toString(), null, new ArrayList<>(userList));
  }

  public void updateUserList(String username) {
    support.firePropertyChange(UserAction.USER_LIST.toString(), null, new ArrayList<>(userList));
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
