package chatsystem.client.core;

import chatsystem.client.model.ChatModel;
import chatsystem.client.model.ChatModelManager;

public class ModelFactory
{
  private ClientFactory cf;
  private ChatModel chatModel;

  public ModelFactory(ClientFactory cf)
  {
    this.cf = cf;
  }

  public ChatModel getChatModel(){
    if (chatModel ==null){
      chatModel = new ChatModelManager(cf.getClient());
    }
    return chatModel;
  }
}
