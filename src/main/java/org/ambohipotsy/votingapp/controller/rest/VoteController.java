package org.ambohipotsy.votingapp.controller.rest;

import java.io.IOException;
import java.util.List;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteMapper;
import org.ambohipotsy.votingapp.controller.validator.NextVoteValidator;
import org.ambohipotsy.votingapp.controller.validator.OtpValidator;
import org.ambohipotsy.votingapp.controller.validator.VoteValidator;
import org.ambohipotsy.votingapp.model.rest.Vote;
import org.ambohipotsy.votingapp.model.rest.VoteAction;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteResult;
import org.ambohipotsy.votingapp.service.ExcelService;
import org.ambohipotsy.votingapp.service.NextVoteService;
import org.ambohipotsy.votingapp.service.OptService;
import org.ambohipotsy.votingapp.service.VoteActionService;
import org.ambohipotsy.votingapp.service.VoteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/vote")
public class VoteController {
    private final VoteService voteService;
    private final VoteActionService voteActionService;
    private final VoteMapper voteMapper;
    private final VoteValidator voteValidator;
    private final NextVoteService nextVoteService;
    private final NextVoteValidator nextVoteValidator;
    private final ExcelService excelService;
    private final OtpValidator otpValidator;
    private final OptService optService;

    @PutMapping("")
    public Vote saveOne(@RequestBody Vote vote) {
        voteValidator.validate(vote);
        return voteMapper.toRest(voteService.saveOne(voteMapper.toDomain(vote)));
    }

    @PutMapping("/{voteId}/makeWithKey")
    public void makeVoteWithKey(@PathVariable String voteId, @RequestParam String key, @RequestBody List<VoteAction> voteActions) {
        otpValidator.validate(key);
        voteActionService.makeVote(voteId, voteActions, key);
        optService.invalidateOtp(key);
    }

    @PutMapping("/{voteId}/make")
    public void makeVote(@PathVariable String voteId, @RequestBody List<VoteAction> voteActions) {
        voteActionService.makeVote(voteId, voteActions, null);
    }

    @PutMapping("/{voteId}/next")
    public void nextVote(@PathVariable String voteId) {
        nextVoteValidator.validate(voteId);
        nextVoteService.createSecondVote(voteId);
    }

    @GetMapping("")
    public List<Vote> getAll(
            @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return voteService.getAll(name).stream().map(voteMapper::toRest).toList();
    }

    @GetMapping("/{voteId}/pv")
    public ResponseEntity<byte[]> downloadPvExcel(@PathVariable String voteId) throws IOException {
        byte[] excelBytes = excelService.getPv(voteId);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PV.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelBytes);
    }

    @GetMapping("/{voteId}/result")
    public VoteResult getResult(@PathVariable String voteId) {
        return voteService.getResult(voteId);
    }

    @GetMapping("/{voteId}")
    public Vote getOne(@PathVariable String voteId) {
        return voteMapper.toRest(voteService.getOne(voteId));
    }
}
