/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Meyrielle
 */
public class Banco {
    String url = "jdbc:sqlite:C:/Users/Meyrielle/Documents/";
    String nomeBanco = "banco.db";
    
    public Connection connect() throws ClassNotFoundException{
        Connection conn = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        
            System.out.println("Connection to SQLite established with "+url);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if(conn != null){
                    conn.close();
                }
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        
        return conn;
    }


    
    public void insert(Profissional profissional) throws ClassNotFoundException{
        System.out.println("insert()");
        System.out.println("\t"+profissional.getNome());
        String sql = "INSERT INTO (nome, idade, profissao, competencia) VALUES (?,?,?,?);";
        
        try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,profissional.getNome());
            pstmt.setInt(2, profissional.getIdade());
            pstmt.setString(3,profissional.getProfissao());
            pstmt.setString(4,profissional.getCompetencia());
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public final void createNewTable() throws SQLException{
         System.out.println("createNewTable()");
        
        String sql = "CREATE TABLE profissional (\n"
                +"  id integer NOT NULL PRIMARY KEY,\n"
                +"  nome varchar(50) NOT NULL,\n"
                +"  idade integer NOT NULL,\n"
                +"  profissao varchar(20) NOT NULL,\n"
                +"  competencia varchar(10) NOT NULL\n"
                +");";
        
        try(Connection conn = DriverManager.getConnection(url); 
                Statement stmt = conn.createStatement()){
            stmt.execute(sql);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public final void createNewBank() throws SQLException{
         System.out.println("createNewBank()");
        
        
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is "+meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public int getId(Profissional profissional) throws SQLException, ClassNotFoundException{
        int id = 0;
        
        List<Profissional> profissionais= new ArrayList<Profissional>();
        
        profissionais = selectAll();
        
        for(Profissional prof : profissionais){
            if(profissional.getNome().equals(prof.getNome())){
                if(profissional.getIdade() == prof.getIdade()){
                    if(profissional.getProfissao().equals(prof.getProfissao())){
                        if(profissional.getCompetencia().equals(prof.getCompetencia())){
                            break;
                        }
                    }
                }
            }
            
            id++;
        }
        
        return id;
    }
    
    public void update(int id, Profissional profissional) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE profissional SET nome = ?,"
                +"idade = ?,"
                +"profissao = ?,"
                +"competencia = ?"
                +" WHERE id = ?;";
        
        try(Connection conn = this.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1,profissional.getNome());
            pstmt.setInt(2,profissional.getIdade());
            pstmt.setString(3,profissional.getProfissao());
            pstmt.setString(4,profissional.getCompetencia());
            
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public void delete(int id) throws ClassNotFoundException{
         System.out.println("delete()");
        String sql = "DELETE FROM profissional WHERE id = ?;";
        
        try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public List<Profissional> selectAll() throws SQLException, ClassNotFoundException{
         System.out.println("selectAll()");
        String sql = "SELECT * from profissional;";
        List<Profissional> profissionais = new ArrayList<Profissional>();
        Profissional profissional;
        
        try(Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                profissional = new Profissional(rs.getString("nome"), rs.getInt("idade"),
                               rs.getString("profissao"), rs.getString("competencia"));
                
                profissionais.add(profissional);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        profissionais.forEach((prof) -> {
            System.out.println(" "+prof.getNome());
        });
        
        return profissionais;
    }
    
    
    
    public final void carregarBanco() throws ClassNotFoundException{
         System.out.println("Carregando banco...");
        List<Profissional> profissionais = new ArrayList(5);
        
        String nomes[] = {"Pedro", "Paulo", "Caio", "Silas", "Pietra"};
        String sobrenomes[] = {"Santos", "Marinho", "Fábio", "Pereira", "Pontes"};
        int[] idades = {45, 26, 20, 59, 33};
        String[] profissoes = {"Vendedor", "Bombeiro", "Desenvolvedor", "Psicólogo", "Analista"};
        String[] competencias = {"Sênior", "Pleno", "Amador", "Sênior", "Pleno"};
        
        for(int i = 0; i < 5; i++){
            String nome = nomes[i] + " " + sobrenomes[i];
            Profissional profissional = new Profissional(nome, idades[i], profissoes[i], competencias[i]);
            profissionais.add(profissional);
        }
        
        for(Profissional p : profissionais){
            insert(p);
        }
    }
    
    
    
    public Banco() throws SQLException, ClassNotFoundException{
        System.out.println("Inicializando banco...");
        //this.nomeBanco = nomeBanco;
        this.url+=this.nomeBanco;
        
        
        //createNewBank();
        //createNewTable();
        //carregarBanco();
    }
}
