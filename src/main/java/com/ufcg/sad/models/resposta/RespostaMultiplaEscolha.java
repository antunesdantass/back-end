package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

import static com.ufcg.sad.models.util.Utils.TAMANHO_MAX_STRING;

@Entity
public class RespostaMultiplaEscolha extends Resposta implements Serializable{

    private static final long serialVersionUID = 1L;

    @OneToOne(mappedBy = "respostaMultiplaEscolha", cascade = CascadeType.PERSIST)
    private Opcao opcaoEscolhida;

    @Length(max = TAMANHO_MAX_STRING)
    private String comentario;

    /**
     * Construtor vazio para o hibernate
     */
    public RespostaMultiplaEscolha() {}

    public RespostaMultiplaEscolha(Date dataResposta, Questao questao, QuestionarioAplicado questionarioAplicado, Opcao opcaoEscolhida, String comentario) {
        super(dataResposta, questao, questionarioAplicado);
        this.opcaoEscolhida = opcaoEscolhida;
        this.comentario = comentario;
    }

    public Opcao getOpcaoEscolhida() {
        return opcaoEscolhida;
    }

    public void setOpcaoEscolhida(Opcao opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RespostaMultiplaEscolha)) return false;
        if (!super.equals(o)) return false;

        RespostaMultiplaEscolha that = (RespostaMultiplaEscolha) o;

        if (getOpcaoEscolhida() != null ? !getOpcaoEscolhida().equals(that.getOpcaoEscolhida()) : that.getOpcaoEscolhida() != null)
            return false;
        return getComentario() != null ? getComentario().equals(that.getComentario()) : that.getComentario() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOpcaoEscolhida() != null ? getOpcaoEscolhida().hashCode() : 0);
        result = 31 * result + (getComentario() != null ? getComentario().hashCode() : 0);
        return result;
    }
}
