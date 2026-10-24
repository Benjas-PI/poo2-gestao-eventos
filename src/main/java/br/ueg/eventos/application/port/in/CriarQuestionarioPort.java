package br.ueg.eventos.application.port.in;

import br.ueg.eventos.application.dto.QuestionarioDTO;
import br.ueg.eventos.domain.model.Questionario;

public interface CriarQuestionarioPort {
    Questionario executar(QuestionarioDTO dto);
}
