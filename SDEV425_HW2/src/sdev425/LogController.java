/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdev425;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sdev425.exceptions.NonexistentEntityException;

/**
 *
 * @author Kalada Opuiyo
 */
public class LogController {

   
    public LogController(EntityManagerFactory emf) {
        this.emf = emf;
    }
   
    private EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionLog transactionLog) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(transactionLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionLog transactionLog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            transactionLog = em.merge(transactionLog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = transactionLog.getId();
                if (findTransactionLog(id) == null) {
                    throw new NonexistentEntityException("The transactionLog with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TransactionLog transactionLog;
            try {
                transactionLog = em.getReference(TransactionLog.class, id);
                transactionLog.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionLog with id " + id + " no longer exists.", enfe);
            }
            em.remove(transactionLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
@SuppressWarnings("unchecked") 
    public List<TransactionLog> findTransactionLogEntities() {
        return findTransactionLogEntities(true, -1, -1);
    }
@SuppressWarnings("unchecked") 
    public List<TransactionLog> findTransactionLogEntities(int maxResults, int firstResult) {
        return findTransactionLogEntities(false, maxResults, firstResult);
    }
@SuppressWarnings("unchecked") 
    private List<TransactionLog> findTransactionLogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionLog.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TransactionLog findTransactionLog(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionLog.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionLog> rt = cq.from(TransactionLog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
