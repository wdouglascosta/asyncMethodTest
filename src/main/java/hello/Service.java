package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@org.springframework.stereotype.Service
public class Service {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    public void method(String imprimir){
        logger.info(imprimir);
    }
}
