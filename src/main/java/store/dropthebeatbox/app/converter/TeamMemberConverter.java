package store.dropthebeatbox.app.converter;


import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.domain.mapping.TeamMember;

public class TeamMemberConverter {

    public static TeamMember toTeamMember(Team team, Member member){
        TeamMember teamMember = TeamMember.builder()
                .member(member)
                .team(team)
                .build();
        return teamMember;
    }
}
