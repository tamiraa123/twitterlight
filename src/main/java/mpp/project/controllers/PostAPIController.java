package mpp.project.controllers;

import mpp.project.Services.TweetService;
import mpp.project.Services.UserServices;
import mpp.project.dao.PostRepository;
import mpp.project.model.Post;
import mpp.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/tweet")
public class PostAPIController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TweetService tweetService;

    @PostMapping(value = "/post/{userid}", consumes = "application/json", produces = "application/json")
    public Post add_tweet( HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody Post p, @PathVariable("userid") int userID )
    {
        try {

            return  tweetService.add_tweet( p, userID);
        }
      catch (Exception e)
      {
          return new Post();
      }
    }

    @DeleteMapping("/post/{postid}/user/{userid}")
    public void delete_post(@PathVariable("userid") int userID,  @PathVariable("postid") int postID)
    {
        tweetService.delete_post(userID, postID);
    }

    @PostMapping(value = "/post/{post}/user/{userid}", consumes = "application/json", produces = "application/json")
    public String addComment(
    HttpServletRequest request,
    HttpServletResponse response,
    @RequestBody Post comment, @PathVariable("userid") int userID, @PathVariable("postid") int postID  )
    {
        try {

            if( tweetService.addComment( postID, comment, userID))
                return "SAVED";
            else
                return  "NOT SAVED";
        }
        catch (Exception e)
        {
            return  "NOT SAVED";
        }
    }

    public void deleteComment(int commentID)
    {

    }
    @GetMapping(value = "/post" )
    public List<Post> getPostsByUser( @RequestParam("userid") int userID )
    {

        return  tweetService.getPostsByUser(userID);
    }

    @GetMapping(value = "/post/wall/{userid}" )
    public List<Post> getWall(@PathVariable("userid") int userID)
    {
        return tweetService.getWall(userID);
    }
}
