/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import DAO.ProfissionalDAO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Profissional;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import DAO.ProfissionalDAOImpl;
import java.sql.SQLException;
import model.Banco;

/**
 *
 * @author Meyrielle
 */
@ManagedBean(name = "profissionalController")
@SessionScoped
public class ProfissionalController {
    private Profissional profissional;
    private DataModel<Profissional> listaProfissionais;
    private String msg;
    
    /**
     *
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @PostConstruct
    public void init(){
        profissional = new Profissional();
    }
    
    public Profissional getProfissional(){
        return profissional;
    }
    
    public DataModel<Profissional> getListaProfissionais() throws SQLException, ClassNotFoundException{
 
        ProfissionalDAOImpl profDAO = new ProfissionalDAOImpl();
                
        List<Profissional> profissionais = profDAO.listarProfissionais();
        
        listaProfissionais = new ListDataModel<Profissional>(profissionais); 
        
        
        return listaProfissionais;
    }
    
    public void setListaProfissionais(DataModel<Profissional> listaProfissionais){
        this.listaProfissionais = listaProfissionais;
    }
    
    public String adicionarProfissional() throws SQLException, ClassNotFoundException{
        ProfissionalDAOImpl profDAO = new ProfissionalDAOImpl();
        profDAO.adicionarProfissional(profissional);
        
        setMsg("Salvo com sucesso!");
        
        return "inserir_profissional_form";
    }
    
    public String excluirProfissional() throws SQLException, ClassNotFoundException{
        Profissional p = (Profissional)(listaProfissionais.getRowData());
        ProfissionalDAOImpl profDAO = new ProfissionalDAOImpl();
        profDAO.excluirDocente(p);
        
        setMsg("Excluido com sucesso!");
        
        return "listar_profissionais";
    }
    
    public String adicionarForm(){
        profissional = new Profissional();
        
        return "inserir_profissional_form";
    }
    
    public String alterarForm(){
        profissional = (Profissional)(listaProfissionais.getRowData());
        
        return "alterar_profissional_form";
    }
    
    public String alterarProfissional() throws SQLException, ClassNotFoundException{
        ProfissionalDAOImpl profDAO = new ProfissionalDAOImpl();
        
        profDAO.alterarProfissional(profissional);
        
        setMsg("Registro alterado com sucesso!");
        
        return "alterar profissional_form";
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    public String listarForm(){
        return "listar_profissionais";
    }
}
