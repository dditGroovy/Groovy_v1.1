package kr.co.groovy.sanction;

import org.springframework.stereotype.Service;

@Service
public class SanctionService {
    final
    SanctionMapper mapper;

    public SanctionService(SanctionMapper mapper) {
        this.mapper = mapper;
    }
}
