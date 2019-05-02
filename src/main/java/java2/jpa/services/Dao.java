package java2.jpa.services;

//package lt.bta.java2.jpa;

import java2.jpa.helpers.EntityManagerHelper;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;


public class Dao<T> implements AutoCloseable {

    final private EntityManager em;
    final private Class<T> clazz;

    // constructor
    public Dao(Class<T> clazz) {
        this.em = EntityManagerHelper.getEntityManager();
        this.clazz = clazz;
    }

    // CRUD

    // CREATE
    public T create(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    }

    // READ override
    public T read(Object pk) {
        T entity = em.find(clazz, pk);
        return entity;
    }

    // READ override
    public T read(Object pk, String graph) {
        EntityGraph entityGraph = em.getEntityGraph(graph);
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(EntityManagerHelper.FETCH_GRAPH, entityGraph);
//        T entity = em.find(clazz, pk, properties);
//        return entity;
        return em.find(clazz, pk, Collections.singletonMap(EntityManagerHelper.FETCH_GRAPH, entityGraph));
    }

    // UPDATE
    public T update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        return entity;
    }

    // DELETE
    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    // READ_LIST
    public List<T> list(int size, int skip) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = em.createQuery(select);
        typedQuery.setFirstResult(skip);
        typedQuery.setMaxResults(size);
        return typedQuery.getResultList();
    }

    // READ_LIST
    public List<T> findBy(String field, Object value) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get(field), value));
        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    // READ_LIST_ALL
    public List<T> listAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = em.createQuery(select);


        return typedQuery.getResultList();
    }

    public void close() {
        em.close();
    }
}



//public class Dao<T> {
////
////    final private EntityManager em;
////    final private Class<T> clazz;
////
////    public Dao(Class<T> clazz) {
////        this.em = EntityManagerHelper.getEntityManager();
////        this.clazz = clazz;
////    }
////
////    public List<T> list(int size, int skip) {
////        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
////        Root<T> from = criteriaQuery.from(clazz);
////        CriteriaQuery<T> select = criteriaQuery.select(from);
////        TypedQuery<T> typedQuery = em.createQuery(select);
////        typedQuery.setFirstResult(skip);
////        typedQuery.setMaxResults(size);
////        return typedQuery.getResultList();
////    }
////
////    public List<T> findBy(String field, Object value) {
////        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
////        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
////        Root<T> from = criteriaQuery.from(clazz);
////        criteriaQuery.select(from);
////        criteriaQuery.where(criteriaBuilder.equal(from.get(field), value));
////        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
////        return typedQuery.getResultList();
////    }
////
////    public T create(T entity) {
////        em.getTransaction().begin();
////        em.persist(entity);
////        em.getTransaction().commit();
////        return entity;
////    }
////
////    public T read(Object pk) {
////        T entity = em.find(clazz, pk);
////        return entity;
////    }
////
////    public T read(Object pk, String graph) {
////        EntityGraph entityGraph = em.getEntityGraph(graph);
////
////
//////        Map<String, Object> properties = new HashMap<>();
//////        properties.put(EntityManagerHelper.FETCH_GRAPH, entityGraph);
//////
//////        T entity = em.find(clazz, pk, properties);
////
////
////        return em.find(clazz, pk, Collections.singletonMap(EntityManagerHelper.FETCH_GRAPH, entityGraph));
////    }
////
////    public T update(T entity) {
////        em.getTransaction().begin();
////        em.merge(entity);
////        em.getTransaction().commit();
////        return entity;
////    }
////
////    public void delete(T entity) {
////        em.getTransaction().begin();
////        em.remove(entity);
////        em.getTransaction().commit();
////    }
//}
//
//import javax.persistence.EntityGraph;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////public class Dao<T>{
////    public List<T> list(Class<T> claz, ){
////
////    }
////        }
//
//public class Dao<T> {
//    final private EntityManager em;
//
//    public Dao(EntityManager em) {
//        this.em = em;
//    }
//    //klase bus specifinio tipo, universali, rasom ja 4 CRUD veiksmams
//                        //galetu visi metodai but static, nes cia niekas nesikuria
//
//
////todo pasiziureti debugu-----------------------------------------------------------
//    public List<T> list(Class<T> clazz, int size, int skip) {
////        EntityManager em = EntityManagerHelper.getEntityManager();
//
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
//        Root<T> from = criteriaQuery.from(clazz);
//
//        CriteriaQuery<T> select = criteriaQuery.select(from);
//        TypedQuery<T> typedQuery = em.createQuery(select);
//        typedQuery.setFirstResult(skip);
//        typedQuery.setMaxResults(size);
//        return typedQuery.getResultList();
//    }
//
//
//
//    public T create(T entity) { //metodui paduosim objekta
//
////        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityManagerHelper.beginTransaction();
//
//        em.persist(entity);
//
//        EntityManagerHelper.commit();
//        EntityManagerHelper.closeEntityManager();
//
//        return entity;
//    }
//
//    public T read(Class<T> clazz, Object pk) { //skaitom pagal pirmini rakta, nereikia skaitymui tranzakciju
////        EntityManager em = EntityManagerHelper.getEntityManager();
//
//        T entity = em.find(clazz, pk);
//        EntityManagerHelper.closeEntityManager();
//
//        return entity;
//    }
//
//    public T readGraph(Class<T> clazz, Object pk, String graph) { //skaitom pagal pirmini rakta, nereikia skaitymui tranzakciju
////        EntityManager em = EntityManagerHelper.getEntityManager();
//
//        EntityGraph entityGraph = em.getEntityGraph(graph);
//        Map<String, Object> properties = new HashMap<>();
//        properties.put(EntityManagerHelper.FETCH_GRAPH, entityGraph);
//
//        T entity = em.find(clazz, pk, properties);
//        //EntityManagerHelper.closeEntityManager();
//
//        return entity;
//    }
//
//    public T update(T entity) {
////        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityManagerHelper.beginTransaction();
//
//        em.merge(entity);
//        EntityManagerHelper.commit();
//        //EntityManagerHelper.closeEntityManager();
//
//        return entity;
//    }
//
//    public void delete(T entity) {
////        EntityManager em = EntityManagerHelper.getEntityManager();
//        EntityManagerHelper.beginTransaction();
//
//        entity = em.merge(entity);
//        em.remove(entity);
//
//        EntityManagerHelper.commit();
//        //EntityManagerHelper.closeEntityManager();
//    }
//
//
//}
