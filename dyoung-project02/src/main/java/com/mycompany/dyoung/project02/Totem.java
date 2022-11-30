package com.mycompany.dyoung.project02;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gucar
 */
public class Totem {
    
    // Objetos
    Conexao con = new Conexao(); // Conexão com o banco de dados
    JdbcTemplate banco = con.getConnection();
    CapituraDeDados cd = new CapituraDeDados();
    
    
    // Atributos
    private Integer idTotem;
    private String loginTotem;
    private String senhaTotem;
    private Integer fk_posto;
    
    
    // Métodos
    public boolean buscarLogin(String usuario, String senha){
        
        boolean possuiCadastro = false;
        
        // Faz uma lista de todos os usuarios
        List<Totem> buscarFuncionario = banco.query("SELECT * FROM totem", 
                new BeanPropertyRowMapper<>(Totem.class));
        
//        for (Totem totem : buscarFuncionario) {
//            if(totem.getLoginTotem().equals(usuario) && totem.getSenhaTotem().equals(senha)){
//                possuiCadastro = true;
//                cd.setVisible(true);
//                cd.setIdTotem(totem.getIdTotem());
//                cd.setFk_posto(totem.getFk_posto());
//            } else {
//                
//            }
//        }
        return possuiCadastro;
    }
    
    // Get e Set
    public Integer getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(Integer idTotem) {
        this.idTotem = idTotem;
    }

    public String getLoginTotem() {
        return loginTotem;
    }

    public void setLoginTotem(String loginTotem) {
        this.loginTotem = loginTotem;
    }

    public String getSenhaTotem() {
        return senhaTotem;
    }

    public void setSenhaTotem(String senhaTotem) {
        this.senhaTotem = senhaTotem;
    }

    public Integer getFk_posto() {
        return fk_posto;
    }

    public void setFk_posto(Integer fk_posto) {
        this.fk_posto = fk_posto;
    }
}
