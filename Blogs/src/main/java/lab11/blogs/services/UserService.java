package lab11.blogs.services;

import lab11.blogs.data.InsertData;
import lab11.blogs.models.User;
import lab11.blogs.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private InsertData insertData;

    /**
     * This function will save or update User.
     * @param user
     */
    @Transactional
    public void save(User user){
        userRepositories.save(user);
    }

    /**
     * This function will find User by Username And Password.
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public User findByUsernameAndPassword(String username, String password){
        return userRepositories.findByUserNameAndPassword(username, password);
    }

    /**
     * This function will call insert() to insert data to database.
     * If already insert it will catch the Exception then nothing happen.
     */
    public void insertData(){
        try {
            insertData.insert();
        } catch (DataIntegrityViolationException e){
            System.out.println(e.getClass());
        }
    }
}
