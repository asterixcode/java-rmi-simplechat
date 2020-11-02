package chatsystem.client.views.chat;

import chatsystem.client.model.ChatModel;
import chatsystem.shared.transferobjects.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import chatsystem.shared.util.Subject;
import chatsystem.shared.util.UserAction;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ChatViewModel implements Subject {
  private ChatModel chatModel;
  private StringProperty send;
  private StringProperty username;
  private PropertyChangeSupport support;

  public ChatViewModel(ChatModel chatModel) {
    this.chatModel = chatModel;

    send = new SimpleStringProperty();
    username = new SimpleStringProperty();

    username.bindBidirectional(chatModel.getUsernameProperty());

    chatModel.addListener(UserAction.BROADCAST.toString(), this::onReceiveMessage);
    chatModel.addListener(UserAction.USER_LIST.toString(), this::createUserList);

    support = new PropertyChangeSupport(this);
  }

  public void createUserList(PropertyChangeEvent event){
    ArrayList<String> users = (ArrayList<String>) event.getNewValue();
    support.firePropertyChange(UserAction.USER_LIST.toString(), null, users);
  }

  private void onReceiveMessage(PropertyChangeEvent event) {
    Message message = (Message) event.getNewValue();
    support.firePropertyChange(UserAction.BROADCAST.toString(), null, message);
  }

  public void sendMessageToServer() {
    Message message = new Message(send.getValue(), getUsername());
    chatModel.sendMessage(message);
  }

  public void getUserList()
  {
    chatModel.getUserList();
  }

  public StringProperty getSendProperty() {
    return send;
  }

  public void disconnect() {
    chatModel.disconnect();
  }

  public String getUsername() {
    return chatModel.getUsername();
  }

  @Override public void addListener(String eventName, PropertyChangeListener listener) {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener) {
    support.removePropertyChangeListener(eventName, listener);
  }
}
