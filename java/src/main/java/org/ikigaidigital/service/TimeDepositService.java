package org.ikigaidigital.service;

import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.TimeDepositCalculator;
import org.ikigaidigital.dto.TimeDepositResponse;
import org.ikigaidigital.entity.TimeDepositEntity;
import org.ikigaidigital.mapper.TimeDepositMapper;
import org.ikigaidigital.repository.TimeDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TimeDepositService {

    @Autowired
    private TimeDepositRepository timeDepositRepository;

    @Autowired
    private TimeDepositCalculator calculator;

    private final ReentrantLock updateLock = new ReentrantLock();

    @Transactional(readOnly = true)
    public List<TimeDepositResponse> getAllTimeDeposits() {

        List<TimeDepositEntity> entities = timeDepositRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        List<TimeDepositResponse> timeDepositResponses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(entities)) {
            timeDepositResponses= entities
                    .stream().map(TimeDepositMapper::toResponse)
                    .toList();
        }
        return timeDepositResponses;

    }

    @Transactional
    public void updateAllBalances() {
        updateLock.lock();
        try {
            List<TimeDepositEntity> entities = timeDepositRepository.findAll();
            if (entities.isEmpty()) {
                return;
            }

            List<TimeDeposit> domainList = new ArrayList<>();
            Map<Integer, TimeDepositEntity> idToEntity = new HashMap<>();
            for (TimeDepositEntity e : entities) {
                domainList.add(new TimeDeposit(e.getId(),
                        e.getPlanType().toLowerCase(),
                        e.getBalance().doubleValue(),
                        e.getDays()));
                idToEntity.put(e.getId(), e);
            }
            calculator.updateBalance(domainList);


            for (TimeDeposit domain : domainList) {
                TimeDepositEntity entity = idToEntity.get(domain.getId());
                if (entity != null) {
                    entity.setBalance(BigDecimal.valueOf(domain.getBalance()));
                    if(entity.getVersion() == null) {
                        entity.setVersion(0L);
                    }
                }
            }

            timeDepositRepository.saveAll(idToEntity.values());
        } finally {
            updateLock.unlock();
        }
    }

}