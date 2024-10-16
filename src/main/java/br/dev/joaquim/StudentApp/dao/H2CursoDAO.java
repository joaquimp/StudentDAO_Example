package br.dev.joaquim.StudentApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.dev.joaquim.StudentApp.entities.Curso;
import br.dev.joaquim.StudentApp.entities.Student;


public class H2CursoDAO implements CursoDAO{
    private Connection connection;
    private String url = "jdbc:h2:file:~/data/students;";
    private String user = "root";
    private String password = "root";
  
    public H2CursoDAO() {
      connect();
      createTableIfNotExists();
    }
  
    private void connect() {
      try {
        this.connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException ex) {
        this.connection = null;
        System.out.println("Problema ao conectar no banco de dados");
        ex.printStackTrace();
      }
    }
  
    private void createTableIfNotExists() {
      try {
        String sql = "CREATE TABLE IF NOT EXISTS cursos(" +
            "id INT, name VARCHAR(256), totAlunos INT, lecionador VARCHAR(256));";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.execute();
      } catch (SQLException ex) {
        System.out.println("Problema ao criar a tabela");
        ex.printStackTrace();
      } catch (NullPointerException ex) {
        System.out.println("Problema ao criar a tabela (sem conexao)");
      }
    }

    @Override
    public boolean create(Curso curso){
      try{
        String sql = "INSERT INTO students VALUES(?,?,?,?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,curso.getiDCurso());
        stmt.setString(2,curso.getNome());
        stmt.setInt(3,curso.getTotalAlunos());
        stmt.setString(4,curso.getLecionador());

        stmt.execute();
        return true;

      }catch (SQLException ex){
        System.out.print("ERROR DE CONEXÃO");
        ex.printStackTrace();
      }catch(NullPointerException ex){
        System.out.print("ERROR AO CRIAR TABELA");
        ex.printStackTrace();
      }  
      return false; 
    }
  
  @Override
   public List<Curso> findAll(){
    try{
    String sql = "SELECT * FROM cursos";
    PreparedStatement stmt = connection.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();

    List<Curso> cursos = new ArrayList<>();


    while(rs.next()){
      int idCurso = rs.getInt("IDCurso ");
      String nomeCurso = rs.getString("NOME DO CURSO ");
      int numeroAlunos = rs.getInt("TOTAL DE ALUNOS ");
      String profess = rs.getString("PROFESSOR: ");

      Curso curso = new Curso(nomeCurso, idCurso, numeroAlunos, profess);

      cursos.add(curso);
    }
    return cursos;

    }catch(SQLException ex){
      System.out.print("ERROR AO BUSCAR ALUNO");
      ex.printStackTrace();
    }
    return new ArrayList<>();
   }
    
  
  @Override
public Curso idCurso(int id) {
    try {
        String sql = "SELECT * FROM cursos WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String nomeCurso = rs.getString("name"); // Altere "name" para o nome correto da coluna no banco de dados
            int totalAlunos = rs.getInt("totAlunos");
            String lecionador = rs.getString("lecionador");
            return new Curso(nomeCurso, id, totalAlunos, lecionador);
        }
    } catch (SQLException ex) {
        System.out.println("ERROR: NÃO FOI POSSÍVEL ENCONTRAR ID");
        ex.printStackTrace();
    }
    return null;
}


  @Override
  public boolean update(Curso curso){
    try{
      String sql = "UPDATE cursos SET id=? WHERE curso=?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setInt(1,curso.getiDCurso());
    stmt.setString(2,curso.getNome());

    stmt.executeUpdate();

    return true;
    }catch(SQLException ex){
      System.out.println("ERROR");
      ex.printStackTrace();
    }
    return false; 
  }

  @Override
  public boolean delete(int id){
    try{
    String sql = "DELETE FROM cursos WHERE id = ?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setInt(1,id);
    stmt.executeUpdate();

    return true;
    }catch(SQLException ex){
      System.out.print("ERROR");
      ex.printStackTrace();
    }
   return false; 
  }

}