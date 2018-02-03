package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositório para Questionário Aplicado
 * 
 * @author Arthur Vinícius
 */
public interface QuestionarioAplicadoRepository extends JpaRepository<QuestionarioAplicado, Long> {

    /**
     * Recupera uma lista de Questionários Aplicados.
     *
     * @param ids
     *          Ids dos Questionários Aplicados a serem recuperados.
     * @return
     *          Lista de Questionários Aplicados.
     */
    List<QuestionarioAplicado> findByIdIn(List<Long> ids);
    
    @Query("SELECT new QuestionarioAplicado(q,"
    		+ "(SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina),"
    		+ "(SELECT d.turma FROM Disciplina d WHERE d.id = q.idDisciplina),"
    		+ "(SELECT d.semestre FROM Disciplina d WHERE d.id = q.idDisciplina))"
    		+ "FROM QuestionarioAplicado q WHERE q.idQuestionario = :idQuestionario")
    List<QuestionarioAplicado> findByIdQuestionario(@Param("idQuestionario")Long idQuestionario);
    
    @Override
    @Query("SELECT new QuestionarioAplicado(q.id, q.idQuestionario, q.idDisciplina,"
    	   + "q.idProfessor,"
    	   + "(SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina))"
    	   + "FROM QuestionarioAplicado q")
    List<QuestionarioAplicado> findAll();
}