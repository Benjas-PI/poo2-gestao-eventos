package br.ueg.eventos.application.dto;

import java.util.List;

public class QuestionarioDTO {
    private String eventoId;
    private String titulo;
    private List<PerguntaDTO> perguntas;
    public QuestionarioDTO() {}
    public String getEventoId() { return eventoId; }
    public void setEventoId(String eventoId) { this.eventoId = eventoId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public List<PerguntaDTO> getPerguntas() { return perguntas; }
    public void setPerguntas(List<PerguntaDTO> perguntas) { this.perguntas = perguntas; }
}
