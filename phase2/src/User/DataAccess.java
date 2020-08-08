package User;

import java.util.UUID;

public interface DataAccess {
    void updateSer(); // write list to ser

    void deSerialize(); // write ser to list

    ClientUser getUser(String name);

    ClientUser getUser(UUID uuid);

    boolean hasUser(String name);

    void addUser(ClientUser clientUser);
}
