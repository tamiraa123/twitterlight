package mpp.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class twitterlight implements CommandLineRunner {

    @Autowired
    AppConfigBean appConfigBean;


    public static void main(String[] args)
    {
        SpringApplication.run(twitterlight.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(appConfigBean.getDb_url());
    }
}
