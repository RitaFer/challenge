package com.athornatus.bussiness.util;

import com.athornatus.domain.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateDeleteList<T extends BaseEntity> {

    private final JpaRepository<T, UUID> repository;

    public void updateList(List<T> actualList, List<T> toKeepList) {

        for (T actual : actualList) {

            boolean found = false;
            for (T keep : toKeepList) {

                if (keep.getId() == null) {
                    continue;
                }

                if (actual.getId() != null && actual.isEqualsId(keep.getId())) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                repository.deleteById(actual.getId());
            }
        }
    }
}