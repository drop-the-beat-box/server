package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.TeamConverter;
import store.dropthebeatbox.app.converter.TeamMemberConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.domain.mapping.TeamMember;
import store.dropthebeatbox.app.repository.TeamMemberRepository;
import store.dropthebeatbox.app.repository.TeamRepository;
import store.dropthebeatbox.app.service.TeamService;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Transactional
    @Override
    public Team create(TeamRequestDto.CreateTeamDto request, Member member) {
        Team team = TeamConverter.toTeam(request, member);
        TeamMember teamMember = TeamMemberConverter.toTeamMember(team, member);
        teamMemberRepository.save(teamMember);
        return teamRepository.save(team);
    }

    @Transactional
    @Override
    public Team update(Long teamId, TeamRequestDto.UpdateTeamDto request) {
        Team team = teamRepository.findById(teamId).get();
        team.setName(request.getName());
        return team;
    }

    @Transactional
    @Override
    public void delete(Long teamId) {
        teamRepository.deleteById(teamId);
        return;
    }

    @Override
    public Team findById(Long teamId) {
        return teamRepository.findById(teamId).get();
    }

    @Override
    public List<Team> findByMember(Member member) {
        List<Long> ids = teamMemberRepository.findTeamIdByMember(member);
        System.out.println("SIZE : " + ids.size());
        return teamRepository.findAllByIdIn(ids);
    }
}
