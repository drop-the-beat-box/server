package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.service.FavoriteFileService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteFileServiceImpl implements FavoriteFileService {
}
