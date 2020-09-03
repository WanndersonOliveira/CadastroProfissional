/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.List;
import model.Profissional;
/**
 *
 * @author Meyrielle
 */
public interface ProfissionalDAO {
    public void adicionarProfissional(Profissional profissional);
    public List<Profissional> listarProfissionais();
    public void excluirDocente(Profissional profissional);
}
