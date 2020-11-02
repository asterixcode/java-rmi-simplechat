package chatsystem.client.model;

import chatsystem.client.network.Client;
import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.UserAction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel {
  private Client client;
  private PropertyChangeSupport support;
  private StringProperty username;

  public ChatModelManager(Client client) {
    this.client = client;
    support = new PropertyChangeSupport(this);
    username = new SimpleStringProperty();

    this.client.addListener(UserAction.BROADCAST.toString(), this::onReceive);
    this.client.addListener(UserAction.USER_LIST.toString(), this::onReceive);

    client.startClient();
  }

  private void onReceive(PropertyChangeEvent event) {
    support.firePropertyChange(event);
  }

  @Override
  public void createUsername(String username) {
    client.login(username);
  }


  @Override
  public void sendMessage(Message message) {
    client.sendMessage(message);
  }


  @Override
  public void getUserList() {
    client.getUserList(username.getValue());
  }

  @Override
  public void disconnect() {
    client.disconnect(username.getValue());
    System.exit(0);
  }

  @Override
  public StringProperty getUsername() {
    return username;
  }

  @Override
  public void setUsername(String username) {
    this.username.setValue(username);
    support.firePropertyChange(UserAction.USERNAME.toString(), null, username);
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
