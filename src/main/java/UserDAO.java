

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> usersList;

    public UserDAO() {
        usersList = new ArrayList<User>();
    }

    public boolean generateDummyList() {
        User generatedUser;
        for (int i = 0; i <= 2; i++) {
            generatedUser = new User("Mohamed" + i, "123" + i);
            usersList.add(generatedUser);
        }
        return true;
    }

    public String findUser(String username) {

        for (User currentUser : usersList) {
            if (currentUser.getUsername().equals(username))
                return currentUser.getPassword();
        }
        return "";
    }
}