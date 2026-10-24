package br.ueg.eventos.application.usecases;

import br.ueg.eventos.application.dto.QuestionarioDTO;
import br.ueg.eventos.application.port.in.CriarQuestionarioPort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.application.port.out.QuestionarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.PerguntaQuestionario;
import br.ueg.eventos.domain.model.Questionario;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CriarQuestionarioUseCase implements CriarQuestionarioPort {
    private final EventoRepositoryPort eventoRepository;
    private final QuestionarioRepositoryPort questionarioRepository;

    public CriarQuestionarioUseCase(EventoRepositoryPort eventoRepository, QuestionarioRepositoryPort questionarioRepository) {
        this.eventoRepository = eventoRepository;
        this.questionarioRepository = questionarioRepository;
    }

    @Override
    public Questionario executar(QuestionarioDTO dto) {
        eventoRepository.buscarPorId(dto.getEventoId())
                .orElseThrow(() -> new DomainRuleException("Evento não encontrado."));
        
        List<PerguntaQuestionario> perguntas = dto.getPerguntas().stream()
                .map(p -> new PerguntaQuestionario(
                        p.getId() != null ? p.getId() : UUID.randomUUID().toString(), 
                        p.getTexto()))
                .collect(Collectors.toList());

        Questionario questionario = Questionario.criar(
                UUID.randomUUID().toString(), 
                dto.getEventoId(), 
                dto.getTitulo(), 
                perguntas
        );
        
        questionarioRepository.salvar(questionario);
        return questionario;
    }
}
