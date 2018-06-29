package net.engining.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.engining.gateway.dao.OutboundJournalDao;
import net.engining.gateway.entity.OutboundJournal;

/**
 * OutboundJournal Service
 */
@Service
@Transactional(readOnly = true)
public class OutboundJournalService extends CrudService<OutboundJournalDao, OutboundJournal> {
}
