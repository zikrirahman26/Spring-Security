package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.MemberRequest;
import com.example.demo.dto.response.MemberResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    public void addMember(MemberRequest request){
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setDescription(request.getDescription());
        memberRepository.save(member);
    }

    public MemberResponse getMemberById(Long id){
        Optional<Member> optMember = memberRepository.findById(id);
        if (optMember != null) {
            Member member = optMember.get();
            return responseMember(member);
        }
        return null;
    }

    public MemberResponse updateMember(MemberRequest request, Long id){
        Optional<Member> optMember = memberRepository.findById(id);
        if (optMember != null) {
            Member member = optMember.get();
            member.setFirstName(request.getFirstName());
            member.setLastName(request.getLastName());
            member.setDescription(request.getDescription());
            memberRepository.save(member);
            return responseMember(member);
        }
        return null;
    }

    public boolean deleteMember(Long id){
        Optional<Member> optMember = memberRepository.findById(id);
        if (optMember != null) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public MemberResponse responseMember(Member member){

        List<ProductResponse> productResponses = member.getProducts()
                .stream()
                .map(t -> new ProductResponse(t.getProductName(), t.getProductModel()))
                .collect(Collectors.toList());

        return MemberResponse.builder()
            .firstName(member.getFirstName())
            .lastName(member.getLastName())
            .description(member.getDescription())
            .products(productResponses)
            .build();
    }
}
