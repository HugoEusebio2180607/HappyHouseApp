package amsi.dei.estg.ipleiria.happy_house.listeners;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.happy_house.modelos.User;

public interface UsersListener {
    void onRefreshListaUsers(ArrayList<User> listaUsers);
    void onUpdateListaUsers(User user);
}
