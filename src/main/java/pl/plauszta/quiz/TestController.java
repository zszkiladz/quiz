package pl.plauszta.quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("permitAll()")
@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @PostMapping(path = "/actuator/shutdown")
    public void shutdown() {
        log.info("/actuator/shutdown Access...");
    }
}
