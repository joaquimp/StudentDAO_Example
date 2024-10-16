package br.dev.joaquim.StudentApp.ihm;

import java.util.Scanner;

import br.dev.joaquim.StudentApp.dao.CursoDAO;
import br.dev.joaquim.StudentApp.entities.Curso;



public class CursoIHM {
    private CursoDAO cursoDao;

    public CursoIHM(CursoDAO cursoDao) {
        this.cursoDao = cursoDao;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Curso Management Menu ===");
            System.out.println("1. Add Curso");
            System.out.println("2. View All Cursos");
            System.out.println("3. Update Curso");
            System.out.println("4. Delete Curso");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addCurso(scanner);
                    break;
                case 2:
                    viewAllCursos();
                    break;
                case 3:
                    updateCurso(scanner);
                    break;
                case 4:
                    deleteCurso(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private void addCurso(Scanner scanner) {
        System.out.print("Enter curso name: ");
        String nomeCurso = scanner.nextLine();
        System.out.print("Enter curso ID: ");
        int idCurso = scanner.nextInt();
        System.out.print("Total alunos do curso: ");
        int alunosCurso = scanner.nextInt();
        System.out.print("Enter name professor: ");
        String profCurso = scanner.next();

        Curso curso = new Curso();
        curso.setNome(nomeCurso);
        curso.setiDCurso(idCurso);
        curso.setTotalAlunos(alunosCurso);
        curso.setLecionador(profCurso);

        cursoDao.create(curso);
        System.out.println("Student added successfully.");
    }

    private void viewAllCursos() {
        System.out.println("=== List of Cursos ===");
        for (Curso curso : cursoDao.findAll()) {
            System.out.println(curso);
        }
    }

    private void updateCurso(Scanner scanner) {
        System.out.print("Enter curso ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
    
        Curso curso = cursoDao.idCurso(id); // Alterado para usar idCurso no DAO
        if (curso == null) {
            System.out.println("Curso not found.");
            return;
        }
    
        // Exibir os valores atuais e permitir que o usuário os atualize
        System.out.println("Current name: " + curso.getNome());
        System.out.print("Enter new name (or press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            curso.setNome(name);
        }
    
        System.out.println("Current total alunos: " + curso.getTotalAlunos());
        System.out.print("Enter new total alunos (or press Enter to keep current): ");
        String alunosInput = scanner.nextLine();
        if (!alunosInput.isEmpty()) {
            int totalAlunos = Integer.parseInt(alunosInput);
            curso.setTotalAlunos(totalAlunos);
        }
    
        System.out.println("Current professor: " + curso.getLecionador());
        System.out.print("Enter new professor name (or press Enter to keep current): ");
        String professor = scanner.nextLine();
        if (!professor.isEmpty()) {
            curso.setLecionador(professor);
        }
    
        // Atualizar curso no banco de dados
        cursoDao.update(curso);
        System.out.println("Curso updated successfully.");
    }
    

    private void deleteCurso(Scanner scanner) {
        System.out.print("Enter curso ID to delete: ");
        int idCurso = scanner.nextInt();
        cursoDao.delete(idCurso);
        System.out.println("Curso deleted successfully.");
    }
    
}
