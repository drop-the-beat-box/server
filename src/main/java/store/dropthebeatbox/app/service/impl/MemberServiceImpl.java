package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.domain.mapping.TeamMember;
import store.dropthebeatbox.app.repository.MemberRepository;
import store.dropthebeatbox.app.repository.TeamMemberRepository;
import store.dropthebeatbox.app.repository.TeamRepository;
import store.dropthebeatbox.app.service.MemberService;
import store.dropthebeatbox.app.web.dto.MemberRequestDto;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final MemberRepository memberRepository;

    final TeamMemberRepository teamMemberRepository;

    final TeamRepository teamRepository;

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Override
    public List<Member> findAllByTeamId(Long teamId) {
        return teamMemberRepository.findMemberByTeamId(teamId);
    }

    @Transactional
    @Override
    public Member insertToTeam(Long memberId, Long teamId) {
        Member member = memberRepository.findById(memberId).get();
        Team team = teamRepository.findById(teamId).get();

        TeamMember teamMember = TeamMember.builder()
                .member(member)
                .team(team)
                .build();
        teamMemberRepository.save(teamMember);
        return member;
    }

    @Override
    public Member update(Long memberId, MemberRequestDto.UpdateMemberDto request) {
        Member member = memberRepository.findById(memberId).get();
        member.setName(request.getName());
        member.setProfileUrl(request.getProfileImage().getName());

        return member;
    }

    @Override
    @Transactional
    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
        return;
    }

}
