package br.com.meta.api.services;

import java.util.List;
import java.util.Optional;

public interface ServiceDao<T> {
    
    /**
     * 
     * @param t
     * @return
     */
    public T inserir (T t);
    
    /**
     * 
     * @param id
     * @return
     */
    public Boolean excluir(Integer id);
    
    /**
     * 
     * @return
     */
    public List<T> listar();
    
    /**
     * 
     * @param id
     * @return
     */
    public Optional<T> buscarPorId(Integer id);
}
