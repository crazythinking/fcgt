package net.engining.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.engining.gateway.dao.InboundJournalDao;
import net.engining.gateway.entity.InboundJournal;

/**
 * InboundJournal Service
 */
@Service
@Transactional(readOnly = true)
public class InboundJournalService extends CrudService<InboundJournalDao, InboundJournal> {
}
