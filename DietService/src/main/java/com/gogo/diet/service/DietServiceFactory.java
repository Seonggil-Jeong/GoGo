package com.gogo.diet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class DietServiceFactory {
    private final List<DietService> dietServices;
    private final Map<String, DietService> dietServiceCache;

    public DietService findServiceImpl(final String role) throws Exception {
        final DietService dietService = dietServiceCache.get(role);
        if (dietService != null) {
            return dietService;
        }

        final DietService foundService = dietServices.stream()
                .filter(service -> service.support(role))
                .findFirst().orElseThrow(NoSuchElementException::new);

        dietServiceCache.put(role, foundService);

        return foundService;

    }
}
