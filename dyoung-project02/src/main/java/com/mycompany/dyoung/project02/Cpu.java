package com.mycompany.dyoung.project02;

import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.text.DecimalFormat;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gucar
 */
public class Cpu {
    
    
    // Objetos
    Conexao con = new Conexao(); // Conexão com o banco de dados
    Processador cpu = new Processador(); // API Looca - pegando dados da CPU
    JdbcTemplate banco = con.getConnection();
    Temperatura temperatura = new Temperatura(); // API Looca - pegando dados da Temperatura da CPU
//    DecimalFormat df = new DecimalFormat("##.00");  // Formatar dados para colocar no banco
    
    
    
    // Atributos
    private Double dadoCpu = 0.0;
    private Double tempUso = 0.0;
    
    
    
    
    // Métodos
    public void inserirDadoCpu(Integer id, Integer fk){
        
        // Adicionando o valor de usop na no atributo
        setDadoCpu(cpu.getUso());
        setTempUso(temperatura.getTemperatura());
        
        
        // Inserindo no banco de dados
        String insert = "INSERT INTO dado_cpu (uso_cpu, status_coleta, fk_totem, fk_posto)VALUES (?, 1, ?, ?);";
        banco.update(insert, getDadoCpu(), 1, 4);
        
        
        // Exibindo no console o resultado
        System.out.println(String.format("Inserindo dado CPU: %.1f ---- ID: %d ---- fkPosto: %d",
                getDadoCpu(), 1, 4));
        
    }
    
    
    
    
    // Get e Set
    public Double getDadoCpu() {
        return dadoCpu;
    }

    public void setDadoCpu(Double dadoCpu) {
        this.dadoCpu = dadoCpu;
    }

    public Double getTempUso() {
        return tempUso;
    }

    public void setTempUso(Double tempUso) {
        this.tempUso = tempUso;
    }
}
