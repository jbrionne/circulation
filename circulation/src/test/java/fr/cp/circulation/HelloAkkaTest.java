package fr.cp.circulation;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import scala.concurrent.duration.Duration;
import akka.actor.ActorSystem;


public class HelloAkkaTest {

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        system.shutdown();
        system.awaitTermination(Duration.create("10 seconds"));
    }

}
