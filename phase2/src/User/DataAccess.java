package User;

import java.util.UUID;

public interface DataAccess {

    Object getObject(String name);

    Object getObject(UUID uuid);

//    boolean hasObject(String name);

    void addObject(Object o);

    void updateSer(); // write list to ser

    void deSerialize(); // write ser to list

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
