package chatsystem.client.model;

import chatsystem.shared.transferobjects.Message;
import chatsystem.shared.util.Subject;
import javafx.beans.property.StringProperty;

public interface ChatModel extends Subject {
  void createUsername(String username);
  void sendMessage(Message message);
  void getUserList();
  void disconnect();
  StringProperty getUsername();
  void setUsername(String username);
}
