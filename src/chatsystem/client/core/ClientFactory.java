package chatsystem.client.core;

import chatsystem.client.network.Client;
import chatsystem.client.network.RMIClient;

public class ClientFactory
{
  private Client client;

  public Client getClient()
  {
    if(client == null){
      client = new RMIClient();
    }
    return client;
  }
}
