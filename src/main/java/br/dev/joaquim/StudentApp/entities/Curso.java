package br.dev.joaquim.StudentApp.entities;

public class Curso{
    private String nome;
    private int iDCurso;
    private int totalAlunos;
    private String lecionador;


    public Curso(){}
    
    public Curso(String nome, int iDCurso, int totalAlunos, String lecionador) {
        this.nome = nome;
        this.iDCurso = iDCurso;
        this.totalAlunos = totalAlunos;
        this.lecionador = lecionador;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getiDCurso() {
        return iDCurso;
    }
    public void setiDCurso(int iDCurso) {
        this.iDCurso = iDCurso;
    }
    public int getTotalAlunos() {
        return totalAlunos;
    }
    public void setTotalAlunos(int totalAlunos) {
        this.totalAlunos = totalAlunos;
    }
    public String getLecionador() {
        return lecionador;
    }
    public void setLecionador(String lecionador) {
        this.lecionador = lecionador;
    }



}