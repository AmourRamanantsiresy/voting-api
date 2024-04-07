package org.ambohipotsy.votingapp.controller.rest;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.Vote;
import org.ambohipotsy.votingapp.service.OptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/otp/generate")
public class OtpController {
    private final OptService optService;

    @GetMapping("")
    public String saveOne() {
        return optService.generateOne().getValue();
    }
}
