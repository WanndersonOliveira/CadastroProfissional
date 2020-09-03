/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Profissional;
import model.*;
/**
 *
 * @author Meyrielle
 */
public class ProfissionalDAOImpl {
    Banco banco;
    //String nomeBanco = "banco.db";
    
    public ProfissionalDAOImpl() throws SQLException, ClassNotFoundException{
        banco = new Banco();
    }
    
    public void adicionarProfissional(Profissional profissional) throws SQLException, ClassNotFoundException{
        banco.insert(profissional);        
    }
    
    public void alterarProfissional(Profissional profissional) throws SQLException, ClassNotFoundException{
        banco.update(banco.getId(profissional), profissional);
    }
    
    public List<Profissional> listarProfissionais() throws SQLException, ClassNotFoundException{
        List<Profissional> profissionais = new ArrayList<Profissional>();
        
        profissionais = banco.selectAll();
        System.out.println("ProfissionalDAO.listarProfissional()");
        
       
        return profissionais;
    }
    
    public void excluirDocente(Profissional profissional) throws SQLException, ClassNotFoundException{
        List<Profissional> profissionais = new ArrayList<Profissional>();
        
        profissionais = banco.selectAll();
        int id = 0;
        
       
        
        for(Profissional prof : profissionais){
            boolean check = ((prof.getNome().equals(profissional.getNome()))
                & (prof.getIdade() == profissional.getIdade())
                & (prof.getProfissao().equals(profissional.getProfissao()))
                & (prof.getCompetencia().equals(profissional.getProfissao())));
             
            if(check == true){
                break;
            }
            
            id++;
        }
        
        banco.delete(id);
    }
    
    
}
