package mpp.project.Services;

import mpp.project.dao.UserRepository;
import mpp.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    @Autowired
    UserRepository userRepository;

    public FollowService() {
    }

    public boolean setUserFollow(int userid, int followUserID) throws Exception
    {
        Optional<User> to = userRepository.findById(userid);
        Optional<User> from = userRepository.findById(followUserID);

        System.out.println("to from " + userid + ", " + followUserID);

        if(to.isPresent() && from.isPresent())
        {
            // Followers follow = new Followers(to.get(), from.get()) ;

//            System.out.println(to.get());
//            System.out.println("");
//            System.out.println(from.get().getFollowers());

            if(to.get().getFollowers().contains(from.get()))
            {
                return false;
            }

            to.get().addFollower(from.get());
            userRepository.save(to.get());

            return true;
        }
        else
            throw new Exception("Not found");


    }


    public boolean setUserUnFollow(int userid, int followUserID) throws Exception
    {
        Optional<User> to = userRepository.findById(userid);
        Optional<User> from = userRepository.findById(followUserID);

        System.out.println("to from " + userid + ", " + followUserID);

        if(to.isPresent() && from.isPresent())
        {

            if(!to.get().getFollowers().contains(from.get()))
            {
                return false;
            }

            to.get().deleteFollower(from.get());
            userRepository.save(to.get());

            return true;
        }
        else
            throw new Exception("Not found");


    }


    public List<User> getFollowers(int id) throws Exception
    {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
        {
            List<User> followers = user.get().getFollowers();
            return followers;

        }
        else
        {
            throw  new Exception("No user found");
        }

    }

    public List<User> getFollowing(int id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {

            List<User> following = user.get().getFollowing();
            return following;

        } else {
            throw new Exception("No user found");
        }

    }
}
