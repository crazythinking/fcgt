/**
 * 
 */
package net.engining.gateway.fts.service.sccc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.sql.SQLQueryFactory;

/**
 * 测试 querydsl sql 方式的batch insert
 * @author luxue
 *
 */
@Service
public class SqlQueryInsert {
	
	@Autowired
	private SQLQueryFactory queryFactory;
	
	@Transactional
	public void batchInsert(){
//		QsqlCtInboundJournal qCtInboundJournal = QsqlCtInboundJournal.ctInboundJournal;
//		QsqlCtInboundJournalHst qCtInboundJournalHst = QsqlCtInboundJournalHst.ctInboundJournalHst;
//		queryFactory.insert(qCtInboundJournalHst)
//			.columns(qCtInboundJournalHst.inboundId, qCtInboundJournalHst.channelId, qCtInboundJournalHst.asynInd)
////			.values("SSS", "A")
//			.select(SQLExpressions.select(qCtInboundJournalHst.inboundId, qCtInboundJournal.channelId, qCtInboundJournal.asynInd).from(qCtInboundJournal))
//			.execute();
//		
//		SQLInsertClause insert = queryFactory.insert(qCtInboundJournalHst);
//		insert.set(qCtInboundJournalHst.inboundId, "111111111").set(qCtInboundJournalHst.channelId, "CCC").set(qCtInboundJournalHst.asynInd, "A").addBatch();
//		insert.set(qCtInboundJournalHst.inboundId, "222222222").set(qCtInboundJournalHst.channelId, "DDD").set(qCtInboundJournalHst.asynInd, "B").addBatch();
//		insert.execute();
		
	}
}
