package es.albarregas.dao;


import es.albarregas.persistencia.HibernateUtil;
import java.io.Serializable;

import java.util.List;
import org.hibernate.HibernateException;

import org.hibernate.Session;


public class GenericoDAO<T> implements IGenericoDAO<T> {
    
    private Session sesion;
    
    private void startTransaction(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }
    
    private void endTransaction(){
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    private void handleExcepcion(HibernateException he) throws HibernateException {
        sesion.getTransaction().rollback();
        throw he;
    } 

    @Override
    public void insertUpdate(T objeto) {
        try {
            startTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
            
        } catch (HibernateException he){
            handleExcepcion(he);
        } finally {
            endTransaction();
        }
    }

    @Override
    public <T> List<T> selectAll(String entidad) {
        List<T> listadoResultados = null;
        try {
            startTransaction();
            listadoResultados = sesion.createQuery(" from " + entidad).list();
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }

    @Override
    public <T> T select(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        
        try {
            startTransaction();
            objetoRecuperado = sesion.get(claseEntidad, pk);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        
        return objetoRecuperado;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            sesion.delete(objeto);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }
    
 }
