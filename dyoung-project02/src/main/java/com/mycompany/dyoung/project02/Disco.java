package com.mycompany.dyoung.project02;

import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.util.Conversor;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gucar
 */
public class Disco {
    
    // Objetos
    Conexao con = new Conexao();  // Conexão com o banco de dados
    JdbcTemplate banco = con.getConnection();
    DiscoGrupo discoGrupo = new DiscoGrupo();  // API Looca - pegando dados do Disco
    DecimalFormat df = new DecimalFormat("##.00");  // Formatar dados para colocar no banco
    
    
    
    //Atributos
    private Double totalDisco = 0.0;
    
    
    
    // Métodos
    public void inseirDadosDisco(Integer id, Integer fk){
        
        // Listando todos os discos da maquina
        List<com.github.britooo.looca.api.group.discos.Disco> discos = discoGrupo.getDiscos();
        
        for (com.github.britooo.looca.api.group.discos.Disco disco : discos) {
            
            //Convertendo os dados para "String" para ficar melhor a transformação em double
            String dadoDiscoTotalString = Conversor.formatarBytes(disco.getTamanho());
            String dadoDiscoEcritaString = Conversor.formatarBytes(disco.getBytesDeEscritas());
            String dadoDiscoLeituraString = Conversor.formatarBytes(disco.getBytesDeLeitura());
            
            
            //Tranformando os dados de "String" para "Double"
            Double dadoDiscoTotalDouble = Double.valueOf(dadoDiscoTotalString.substring(0, 3));
            Double dadoDiscoEcritaDouble = Double.valueOf(dadoDiscoEcritaString.substring(0, 1));
            Double dadoDiscoLeituraDouble = Double.valueOf(dadoDiscoLeituraString.substring(0, 1));
            
            
            // Realizando a conta para calcular a porcentagem de uso
            Double totalUso = dadoDiscoEcritaDouble + dadoDiscoLeituraDouble;
            totalDisco = (totalUso * 100) / dadoDiscoTotalDouble;
            
            
            //Inserindo os dados no banco
            String insertDisco = "INSERT INTO dado_disco (uso_disco, status_coleta, fk_totem, fk_posto)VALUES (?, 1, ?, ?);";
            banco.update(insertDisco, df.format(getTotalDisco()), 1, 4);
            
            
            //Exibindo os dados
            System.out.println(String.format("Inserindo dado do Disco: %.1f", getTotalDisco()));
            
        }
    }
    
    
    
    // Get e Set
    public Double getTotalDisco() {
        return totalDisco;
    }

    public void setTotalDisco(Double totalDisco) {
        this.totalDisco = totalDisco;
    }
}
