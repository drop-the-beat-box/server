package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.TeamConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.repository.TeamRepository;
import store.dropthebeatbox.app.service.TeamService;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    @Override
    public Team create(TeamRequestDto.CreateTeamDto request, Member member) {
        Team team = TeamConverter.toTeam(request, member);
        return teamRepository.save(team);
    }
}
