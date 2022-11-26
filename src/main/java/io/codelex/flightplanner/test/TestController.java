package io.codelex.flightplanner.test;

import io.codelex.flightplanner.common.CommonService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/testing-api")
public class TestController {

    private final CommonService commonService;

    public TestController(CommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping("/clear")
    void clear() {
        commonService.clear();
    }
}
