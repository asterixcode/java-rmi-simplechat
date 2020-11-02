package chatsystem.client.views;

import chatsystem.client.core.ViewHandler;
import chatsystem.client.core.ViewModelFactory;

public interface ViewController
{
  void init(ViewHandler vh, ViewModelFactory vmf);
}
