package mpp.project.Services;

import mpp.project.dao.UserRepository;
import mpp.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Entity;

import java.util.*;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public UserServices() {
    }

    public User getUserByID (int id) throws Exception
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
            return user.get();
        else
            throw new Exception( String.format("User not found userID: {0}", id));
    }



    public String AddUpdateUser(User entity)
    {
        Optional<User> user =  userRepository.findById(entity.getUserid());
        if(user.isPresent())
        {
            System.out.println("@@1");
            User newEntity = user.get();

            newEntity.setEmail(entity.getEmail());
            //newEntity.setUserpassword(entity.getUserpassword());
            newEntity.setIsActive(entity.getIsActive());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setLocation(entity.getLocation());
            newEntity.setBirthDay(entity.getBirthDay());

            newEntity = userRepository.save(newEntity);
            return "UPDATED";
        }
        else
        {
            List<User> AllUserList = userRepository.findAll();
            for (User u:AllUserList) {
                if(u.getUserName().contains(entity.getUserName()))
                {
                    return "DUPLICATE";
                }
            }
            entity = userRepository.save(entity);
            return "SUCCESS";
        }

    }


    public boolean SetPassword(int id , String newPassword )
    {
        System.out.println("id:"+id + " newPassword:"+newPassword);
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent())
        {
            user.get().setUserpassword(newPassword);
            userRepository.save(user.get());
            return true;
        }
        else
        {
            return false;
        }
    }

    public void deleteUser(int id) throws Exception
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
        {
            userRepository.deleteById(id);
        }
        else
            throw new Exception("Not found");
    }

    public List<User> getAllUser()
    {
        List<User> users = userRepository.findAll();
        if(users.size() == 0) return new ArrayList<User>();
        else
            return users;
    }

    public User signIn (String userName, String Password) throws Exception
    {

        Iterator it = userRepository.findAll().iterator();

        while (it.hasNext())
        {
            User u = (User) it.next();

           if(u.getUserName().equals(userName) || u.getEmail().equals(userName) )
           {
               System.out.println(u.getUserpassword() + " Pppppppp" + Password) ;
               if(u.getUserpassword().equals(Password))
               {
                   return u;
               }
           }
        }
        return new User();
    }


}
