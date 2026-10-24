package br.ueg.eventos.application.dto;

import br.ueg.eventos.domain.model.ModalidadeEvento;
import br.ueg.eventos.domain.model.TipoEvento;

import java.time.ZonedDateTime;

public class EventoDTO {

    private String idOrganizador;
    private String titulo;
    private String descricao;
    private TipoEvento tipo;
    private ModalidadeEvento modalidade;
    private String endereco;
    private ZonedDateTime dataInicio;
    private ZonedDateTime dataFim;

    public EventoDTO() {
    }

    public EventoDTO(
            String idOrganizador,
            String titulo,
            String descricao,
            TipoEvento tipo,
            ModalidadeEvento modalidade,
            String endereco,
            ZonedDateTime dataInicio,
            ZonedDateTime dataFim
    ) {
        this.idOrganizador = idOrganizador;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.modalidade = modalidade;
        this.endereco = endereco;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getIdOrganizador() { return idOrganizador; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public TipoEvento getTipo() { return tipo; }
    public ModalidadeEvento getModalidade() { return modalidade; }
    public String getEndereco() { return endereco; }
    public ZonedDateTime getDataInicio() { return dataInicio; }
    public ZonedDateTime getDataFim() { return dataFim; }

    public void setIdOrganizador(String idOrganizador) { this.idOrganizador = idOrganizador; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }
    public void setModalidade(ModalidadeEvento modalidade) { this.modalidade = modalidade; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setDataInicio(ZonedDateTime dataInicio) { this.dataInicio = dataInicio; }
    public void setDataFim(ZonedDateTime dataFim) { this.dataFim = dataFim; }
}
