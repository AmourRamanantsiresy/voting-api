package org.ambohipotsy.votingapp.controller.rest;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.OtpMapper;
import org.ambohipotsy.votingapp.controller.validator.OtpValidator;
import org.ambohipotsy.votingapp.model.rest.Otp;
import org.ambohipotsy.votingapp.service.OptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/otp")
public class OtpController {
    private final OptService optService;
    private final OtpValidator otpValidator;
    private final OtpMapper otpMapper;

    @GetMapping("/generate/{voteId}")
    public String saveOne(@PathVariable String voteId) {
        return optService.generateOneWithVote(voteId).getValue();
    }

    @GetMapping("/validate")
    public Otp validate(@RequestParam String key) {
        otpValidator.validate(key);
        return otpMapper.toRest(optService.getOneByValue(key));
    }
}
