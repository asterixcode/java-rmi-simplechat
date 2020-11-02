package chatsystem.client;

import chatsystem.client.core.ClientFactory;
import chatsystem.client.core.ModelFactory;
import chatsystem.client.core.ViewHandler;
import chatsystem.client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class ChatSystemApp extends Application
{
  @Override public void start(Stage stage) throws Exception
  {
    ClientFactory cf = new ClientFactory();
    ModelFactory mf = new ModelFactory(cf);
    ViewModelFactory vmf = new ViewModelFactory(mf);
    ViewHandler vh = new ViewHandler(vmf);
    vh.start();
  }
}
