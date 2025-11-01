package org.ikigaidigital.service;
import org.ikigaidigital.TimeDeposit;
import org.ikigaidigital.TimeDepositCalculator;
import org.ikigaidigital.entity.TimeDepositEntity;
import org.ikigaidigital.repository.TimeDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;
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
    public List<TimeDepositEntity> getAllTimeDeposits() {
        return timeDepositRepository.findAll();
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
                        e.getPlanType(),
                        e.getBalance().doubleValue(),
                        e.getDays()));
                idToEntity.put(e.getId(), e);
            }
            calculator.updateBalance(domainList);


            for (TimeDeposit domain : domainList) {
                TimeDepositEntity entity = idToEntity.get(domain.getId());
                if (entity != null) {
                    entity.setBalance(BigDecimal.valueOf(domain.getBalance()));
                }
            }

            timeDepositRepository.saveAll(idToEntity.values());
        } finally {
            updateLock.unlock();
        }
    }

}