package chatsystem.shared.transferobjects;

import java.io.Serializable;

public class Message implements Serializable {
  private String messageBody;
  private String username;

  public Message(String messageBody, String username) {
    this.messageBody = messageBody;
    this.username = username;
  }

  public String getMessageBody()
  {
    return messageBody;
  }

  public String getUsername()
  {
    return username;
  }

  @Override
  public String toString()
  {
    return username + ": " + messageBody;
  }
}
