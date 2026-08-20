package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inscricao {

  private final String id;
  private final LocalDateTime dataHoraRegistro;

  private Usuario participante;
  private Evento evento;
  private StatusInscricao situacao;

  private List<Atividade> atividadesSelecionadas;

  protected Inscricao(String id, Usuario participante, Evento evento) {
    if (id == null || participante == null || evento == null) {
      throw new DomainRuleException("Dados incompletos para gerar a inscrição.");
    }

    this.id = id;
    this.participante = participante;
    this.evento = evento;
    this.dataHoraRegistro = LocalDateTime.now();

    this.situacao = StatusInscricao.PENDENTE; 
    this.atividadesSelecionadas = new ArrayList<>();
  }

  public List<Atividade> getAtividadesSelecionadas() {
    return Collections.unmodifiableList(this.atividadesSelecionadas);
  }

}
