/*package com.patroclos.config;


@Stateful
public class teste implements TesteInterface {
    DataSource ds;
    Connection conn;
     
    @PostConstruct
    public void init() {
        // adquire o data source
        // ...
        adquireConexao();
    }
 
    @PrePassivate
    public void passivate() { liberarConexao (); }
 
    @PostActivate
    public void activate() { adquireConexao(); }
     
    @PreDestroy
    public void shutdown() { liberarConexao(); }
        private void adquireConexao() {
         
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            throw new EJBException(e);
        }
 
    }
 
    private void liberarConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
        }
 
        conn = null;
    }
 
    public Collection<Order> listarPedidos() {
        // ...
    }
}
*/