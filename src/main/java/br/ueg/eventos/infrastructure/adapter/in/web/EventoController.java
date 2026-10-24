package br.ueg.eventos.infrastructure.adapter.in.web;

import br.ueg.eventos.application.port.in.CadastrarAtividadePort;
import br.ueg.eventos.application.port.in.PublicarEventoPort;
import br.ueg.eventos.application.port.in.CriarEventoPort;
import br.ueg.eventos.application.dto.EventoDTO;
import br.ueg.eventos.application.dto.CadastrarAtividadeDTO;
import io.javalin.http.Context;

import br.ueg.eventos.domain.model.Atividade;
import br.ueg.eventos.domain.model.Evento;

import java.util.Map;

public class EventoController {
    
    private final CriarEventoPort criarEventoPort;
    private final PublicarEventoPort publicarEventoPort;
    private final CadastrarAtividadePort cadastrarAtividadePort;

    public EventoController(CriarEventoPort criarEventoPort,
                            PublicarEventoPort publicarEventoPort,
                            CadastrarAtividadePort cadastrarAtividadePort) {
        this.criarEventoPort = criarEventoPort;
        this.publicarEventoPort = publicarEventoPort;
        this.cadastrarAtividadePort = cadastrarAtividadePort;
    }

    public void criar(Context ctx) {
        try {
            EventoDTO dto = ctx.bodyAsClass(EventoDTO.class);

            Evento eventoCriado = criarEventoPort.executar(dto);
            
            ctx.status(201).json(Map.of(
                "mensagem", "Evento criado com sucesso!",
                "id", eventoCriado.getId()
            ));
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno ao criar evento: " + e.getMessage());
        }
    }

    public void publicar(Context ctx) {
        try {
            String idEvento = ctx.pathParam("id");
            publicarEventoPort.executar(idEvento);
            
            ctx.status(200).result("Evento publicado com sucesso!");
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno ao publicar evento: " + e.getMessage());
        }
    }

    public void cadastrarAtividade(Context ctx) {
        try {
            String idEvento = ctx.pathParam("id");
            CadastrarAtividadeDTO dto = ctx.bodyAsClass(CadastrarAtividadeDTO.class);

            Atividade atividadeCriada = cadastrarAtividadePort.executar(
                    idEvento,
                    dto.getTitulo(),
                    dto.getDescricao(),
                    dto.getTipo(),
                    dto.getDataInicio(),
                    dto.getDataFim(),
                    dto.getLocal()
            );

            ctx.status(201).json(Map.of(
                "mensagem", "Atividade cadastrada com sucesso!",
                "id", atividadeCriada.getId()
            ));
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno ao cadastrar atividade: " + e.getMessage());
        }
    }
}
