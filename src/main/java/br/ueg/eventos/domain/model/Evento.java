package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DomainRuleException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.time.ZonedDateTime;

public class Evento {
  private final String id;
  private String titulo;
  private String descricao;
  private String local;
  private ZonedDateTime dataInicio;
  private ZonedDateTime dataFim;
  private StatusEvento situacao;
  private List<Atividade> atividades;


  protected Evento(String id, String titulo, LocalDateTime dataInicio, LocalDateTime dataFim) {
    if (textOuVazio(id)) {
      throw new DomainRuleException("Id da entidade não pode ser nulo ou vazio.");
    }
    
    if (textOuVazio(titulo)) {
      throw new DomainRuleException("O título do evento é obrigatório.");
    }

    if(textOuVazio(descricao)) {
      throw new DomainRuleException("O Evento precisa ter uma descrição!");
    }

    if(textOuVazio(local)) {
      throw new DomainRuleException("É preciso definir um local para o evento");
    }

    if (dataFim == null || dataInicio == null) {
      throw new DomainRuleException("As datas de iníco e término são obrigatórias!");
    }
    
    if (dataFim.isBefore(dataInicio)) {
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

  public Boolean textOuVazio(String text) {
    return text == null || text.isblanck();
  }
}


