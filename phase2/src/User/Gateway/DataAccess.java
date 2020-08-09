package User.Gateway;


import User.Entity.ClientUser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface DataAccess {

    List<Object> getList();

    Object getObject(String name);

    Object getObject(UUID uuid);

    void addObject(Object o) throws FileNotFoundException;

    boolean hasObject(Object o);

    void removeObject(Object o);

    void updateSer() throws FileNotFoundException; // write list to ser

    void deSerialize(); // write ser to list

    void setList(List<Object> userList);



//    boolean hasObject(String name);

//    ClientUser getUser(String name);
//
//    ClientUser getUser(UUID uuid);
//
//    boolean hasUser(String name);
//
//    void addUser(ClientUser clientUser);
//
//    void add(Object object);
}
