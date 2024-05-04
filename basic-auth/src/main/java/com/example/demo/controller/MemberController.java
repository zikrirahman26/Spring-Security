package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.MemberRequest;
import com.example.demo.dto.response.MemberResponse;
import com.example.demo.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    @Autowired
    private MemberService  memberService;

    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody MemberRequest request){
        
        memberService.addMember(request);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@RequestBody MemberRequest request, @PathVariable Long id){
        MemberResponse result = memberService.updateMember(request, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id){
        MemberResponse result = memberService.getMemberById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id){
        boolean result = memberService.deleteMember(id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
