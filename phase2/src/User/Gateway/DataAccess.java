package User.Gateway;


import User.Entity.ClientUser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface DataAccess {

    List<Object> getList();

    Object getObject(String name);

    Object getObject(UUID uuid);

    void addObject(Object o);

    boolean hasObject(Object o);

    void removeObject(String o);

    void removeObject(UUID o);

    void updateSer(); // write list to ser

    void deSerialize(); // write ser to list

    void setList(List<Object> list);


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
