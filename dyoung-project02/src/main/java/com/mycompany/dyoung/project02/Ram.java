package com.mycompany.dyoung.project02;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import java.text.DecimalFormat;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gucar
 */
public class Ram {
    
    //Objetos
    Memoria mem = new Memoria();  // API Looca - pegando dados da Ram
    Conexao con = new Conexao();  // Conexão com o banco de dados
    JdbcTemplate banco = con.getConnection();
//    DecimalFormat df = new DecimalFormat("##.00");  // Formatar dados para colocar no banco
    
    
    // Atributos
    private Double totalRam = 0.0;
    
    
    public void inserirDadosRam(Integer id, Integer fk){
        
        //Pegando os dados da RAM = Memória RAM transformando, exibindo 
        //e guardando no banco de dados
        //Os dados da memoria chegam em "LONG", porem temos que converter
        //para double e fazer a conta para calcular a porcentagem de uso
        Long dadoMemoriaRam = mem.getEmUso();
        Long dadoTotalMemoriaRam = mem.getTotal();
        

        //Convertendo os dados para "String" para ficar melhor a transformação em double
        String dadoRamString = Conversor02.formatarBytes(dadoMemoriaRam);
        String dadoTotalRamString = Conversor02.formatarBytes(dadoTotalMemoriaRam);
        
        
        //Tranformando os dados de "String" para "Double"
        Double dadoRamDouble = Double.valueOf(dadoRamString);
        Double dadoTotalRamlDouble = Double.valueOf(dadoTotalRamString);
        

        // Realizando a conta para calcular a porcentagem de uso
        setTotalRam((dadoRamDouble * 100) / dadoTotalRamlDouble);
        
        
        //Inserindo os dados no banco
        String insertRam = "INSERT INTO dado_ram (uso_ram, status_coleta, fk_totem, fk_posto)VALUES (?, 1, ?, ?);";
        banco.update(insertRam, getTotalRam(), 1, 4);
        
                
        //Exibindo os dados
        System.out.println(String.format("Inserindo dado da Memória RAM: %.1f", getTotalRam()));
        
    }
    
    
    
    // Get e Set
    public Double getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(Double totalRam) {
        this.totalRam = totalRam;
    }
    
}
