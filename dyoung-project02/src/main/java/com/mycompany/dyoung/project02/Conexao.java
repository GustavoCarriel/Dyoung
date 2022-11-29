package com.mycompany.dyoung.project02;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gucar
 */
public class Conexao {
    private JdbcTemplate connection;
    
    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();
        
        // Banco LOCAL
        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");  // Driver
        
        dataSource​.setUrl("jdbc:mysql://localhost:3306/dyoung");  // URL
        
        dataSource​.setUsername("root");  // Usuario
        
        dataSource​.setPassword("@Gustavo08");  // Senha
        
        
        //Banco na NUVEM
//        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  // Driver
//        
//        dataSource​.setUrl(  // URL
//                "jdbc:sqlserver://dyoung-bd.database.windows.net:1433;database=dyoung-bd;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");
//
//        dataSource​.setUsername("admin-dyoung");  // Usuario
//
//        dataSource​.setPassword("#Gfgrupo6");  // Senha
        
        
        this.connection = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConnection() {
        return connection;
    }
}
