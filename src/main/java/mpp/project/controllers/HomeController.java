package mpp.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HomeController {

    @Autowired
    Environment environment;

    @RequestMapping("/")
    public String Hello() throws UnknownHostException
    {
        String hostname = InetAddress.getLocalHost().getHostName();
        String port = environment.getProperty("local.server.port");
        return "helloworld" +" hostname:" +hostname +" port:"+ port;
    }
}
