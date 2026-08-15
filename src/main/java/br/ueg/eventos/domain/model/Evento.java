package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.util.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
  private final String id;
  private String titulo;
  private LocalDateTime dataInicio;
  private LocalDateTime dataFim;
  private StatusEvento situacao;
  private List<Atividade> atividades;


  protected Evento(String id, LocalDateTime dataInicio, LocalDateTime dataFim, String titulo) {
    if (id == null || id.isBlank()) {
      throw new DomainRuleException("Id da entidade não pode ser nulo ou vazio.");
    }
    if (titulo == null || titulo.isBlank()) {
      throw new DomainRuleException("O título do evento é obrigatório.");
    }
    if (dataInicio != null && dataFim != null && dataFim.isBefore(dataInicio)) {
      throw new DomainRuleException("A data de término não pode ser anterior à data de início.");
    }
    this.id = id;
    this.titulo = titulo;
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.situacao = StatusEvento.RASCUNHO;
    this.atividades = new ArrayList<>();
  }

  public static Evento criarNovo(String id, String titulo, LocalDateTime inicio, LocalDateTime fim) {
    return new Evento(id, titulo, inicio, fim);
  }

  public List<Atividade> getAtividades() {
    return Collections.listaAtividades(this.atividades);
  }

  public void adicionarAtividade(Atividade novaAtividade) {
    if (novaAtividade == null) {
      throw new DomainRuleException("A atividade não pode ser nula.");
    }
    if (this.situacao == StatusEvento.ENCERRADO) {
      throw new DomainRuleException("Não é possível adicionar atividades em um evento encerrado.");
    }
    this.atividades.add(novaAtividade);
  }
}


