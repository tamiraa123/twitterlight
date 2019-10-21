package mpp.project.Services;

import mpp.project.dao.PostRepository;
import mpp.project.dao.UserRepository;
import mpp.project.model.Post;
import mpp.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TweetService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public Post add_tweet(Post p, int userID )  throws Exception
    {
        Optional<User> user =  userRepository.findById(userID);

        if(user.isPresent())
        {
            user.get().getPosts().add(p);
            p.getUsers().add(user.get());
            postRepository.save(p);
            return p;
        }
        else
        {
            throw new Exception("User is not present");
        }
    }

    public boolean delete_post(int userID, int postID)
    {
        Optional<User> user =  userRepository.findById(userID);

        if(user.isPresent())
        {
            Optional<Post> post =  postRepository.findById(postID);

            if(post.isPresent())
            {
                user.get().getPosts().remove(post);
                userRepository.save(user.get());
                return true;
            }
            else
                return false;
        }
        else
        {
            return false;

        }
    }

    public boolean addComment(int postID, Post comment, int userID)
    {
        Optional<User> user =  userRepository.findById(userID);

        if(user.isPresent())
        {
          for(  int i = 0; i < user.get().getPosts().size(); i ++)
          {
                if(user.get().getPosts().get(i).getPostID() == postID)
                {
                    user.get().getPosts().get(i).getComments().add(comment);

                    userRepository.save(user.get());

                    return true;
                }
          }
           return false;
        }
        else
        {
           return false;
        }
    }

    public void deleteComment(int commentID)
    {

    }

    public List<Post> getPostsByUser(int userID)
    {
        Optional<User> user =  userRepository.findById(userID);

        List<Post> n = new ArrayList<>();

        if(user.isPresent())
        {
            n.addAll( user.get().getPosts());
            Collections.sort(n, new compUserDate());
            return n;
        }

        return n;
    }

    public List<Post> getWall(int userID)
    {
        Optional<User> user =  userRepository.findById(userID);

        List<Post> n = new ArrayList<Post>();

        if(user.isPresent())
        {

            for (User u: user.get().getFollowing())
            {
                n.addAll(u.getPosts());

                for(int i = 0; i < u.getPosts().size(); i ++)
                {
                    u.getPosts().get(i).setUserName(u.getUserName());
                }

            }

            Collections.sort(n, new compUserDate());
            return n;
       }

        return n;
    }

    public class compUserDate implements Comparator<Post> {

        @Override
        public int compare(Post o1, Post o2) {
            return o2.getPostDate().compareTo(o1.getPostDate());
            }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }


}
