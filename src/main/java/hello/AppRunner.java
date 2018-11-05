package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookupService gitHubLookupService;

    @Autowired
    Service service;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        service.method("imprimiu na thread principal");
        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        System.out.println("passou o primeiro");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        System.out.println("passou o segundo");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
        System.out.println("passou o terceiro");
        CompletableFuture<User> page4 = gitHubLookupService.findUser("wdouglascosta");
        System.out.println("passou o quarto");

        CompletableFuture<String> text = gitHubLookupService.laco("primeiro laco",500);
        System.out.println("------------------");
        gitHubLookupService.laco("segundo laco",5000);


        // Wait until they are all done
        CompletableFuture.allOf(page1,page2,page3,page4).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
        logger.info("--> " + page4.get());

    }

}