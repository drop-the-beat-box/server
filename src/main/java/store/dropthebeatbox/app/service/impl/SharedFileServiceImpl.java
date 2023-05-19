package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.SharedFileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;
import store.dropthebeatbox.app.repository.FileRepository;
import store.dropthebeatbox.app.repository.MemberRepository;
import store.dropthebeatbox.app.repository.SharedFileRepository;
import store.dropthebeatbox.app.service.SharedFileService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SharedFileServiceImpl implements SharedFileService {
    private final SharedFileRepository sharedFileRepository;
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Member> findByFileId(Long fileId) {
        return memberRepository.findSharedMembersByFile_Id(fileId);
    }

    @Transactional
    @Override
    public SharedFile create(Long fileId, Long memberId) {
        File file = fileRepository.findById(fileId).get();
        Member member = memberRepository.findById(memberId).get();
        SharedFile sharedFile = SharedFileConverter.toSharedFile(file, member);
        return sharedFileRepository.save(sharedFile);
    }

    @Transactional
    @Override
    public void delete(Long fileId, Long memberId) {
        sharedFileRepository.deleteByFile_IdAndMember_Id(fileId, memberId);
        return;
    }
}
