package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.TeamMemberConverter;
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

    private final MemberRepository memberRepository;

    private final TeamMemberRepository teamMemberRepository;

    private final TeamRepository teamRepository;

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

        TeamMember teamMember = TeamMemberConverter.toTeamMember(team, member);
        teamMemberRepository.save(teamMember);
        return member;
    }
    
    @Transactional
    @Override
    public Member update(Long memberId, MemberRequestDto.UpdateMemberDto request) {
        Member updatedMember = memberRepository.findById(memberId).get();
        updatedMember = updatedMember.SetNameAndProfile(request.getName(), request.getProfileImage().getName());

        return updatedMember;
    }

    @Override
    @Transactional
    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
        return;
    }

}
