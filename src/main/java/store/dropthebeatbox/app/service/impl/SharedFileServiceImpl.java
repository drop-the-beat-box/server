package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.SharedFileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.domain.mapping.SharedFile;
import store.dropthebeatbox.app.repository.FileRepository;
import store.dropthebeatbox.app.repository.MemberRepository;
import store.dropthebeatbox.app.repository.SharedFileRepository;
import store.dropthebeatbox.app.repository.TeamRepository;
import store.dropthebeatbox.app.service.SharedFileService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SharedFileServiceImpl implements SharedFileService {
    private final SharedFileRepository sharedFileRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    private final TeamRepository teamRepository;


    @Transactional
    @Override
    public SharedFile create(Long fileId, Long teamId) {
        File file = fileRepository.findById(fileId).get();
        Team team = teamRepository.findById(teamId).get();
        SharedFile sharedFile = SharedFileConverter.toSharedFile(file, team);
        return sharedFileRepository.save(sharedFile);
    }

    @Override
    public List<File> findByTeam(Long teamId) {
        return sharedFileRepository.findFilesByTeam_Id(teamId);
    }

    @Transactional
    @Override
    public void delete(Long fileId, Long teamId) {
        sharedFileRepository.deleteByFile_IdAndTeam_Id(fileId, teamId);
        return;
    }
}
