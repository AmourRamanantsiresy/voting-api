package org.ambohipotsy.votingapp.controller.rest;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.validator.OtpValidator;
import org.ambohipotsy.votingapp.service.OptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/otp")
public class OtpController {
  private final OptService optService;
  private final OtpValidator otpValidator;

  @GetMapping("/generate")
  public String saveOne() {
    return optService.generateOne().getValue();
  }

  @GetMapping("/validate")
  public void validate(@RequestParam String key) {
    otpValidator.validate(key);
  }
}
