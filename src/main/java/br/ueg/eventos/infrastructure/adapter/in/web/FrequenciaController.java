package br.ueg.eventos.infrastructure.adapter.in.web;

import br.ueg.eventos.application.dto.RegistroManualDTO;
import br.ueg.eventos.application.dto.RegistroQRCodeDTO;
import br.ueg.eventos.application.dto.SituacaoPresencaDTO;
import br.ueg.eventos.application.port.in.CalcularSituacaoPresencaPort;
import br.ueg.eventos.application.port.in.RegistrarFrequenciaManualPort;
import br.ueg.eventos.application.port.in.RegistrarFrequenciaQRCodePort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import io.javalin.http.Context;

import java.util.Map;

public class FrequenciaController {
    private final RegistrarFrequenciaManualPort registrarManualPort;
    private final RegistrarFrequenciaQRCodePort registrarQRCodePort;
    private final CalcularSituacaoPresencaPort calcularSituacaoPresencaPort;

    public FrequenciaController(RegistrarFrequenciaManualPort registrarManualPort,
                                RegistrarFrequenciaQRCodePort registrarQRCodePort,
                                CalcularSituacaoPresencaPort calcularSituacaoPresencaPort) {
        this.registrarManualPort = registrarManualPort;
        this.registrarQRCodePort = registrarQRCodePort;
        this.calcularSituacaoPresencaPort = calcularSituacaoPresencaPort;
    }

    public void registrarManual(Context ctx) {
        try {
            RegistroManualDTO dto = ctx.bodyAsClass(RegistroManualDTO.class);
            registrarManualPort.executar(dto.getParticipanteId(), dto.getAtividadeId(), dto.getResponsavelId());
            ctx.status(200).json(Map.of("mensagem", "Frequência manual registrada com sucesso"));
        } catch (IllegalArgumentException | DomainRuleException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }

    public void registrarPorQRCode(Context ctx) {
        try {
            RegistroQRCodeDTO dto = ctx.bodyAsClass(RegistroQRCodeDTO.class);
            registrarQRCodePort.executar(dto.getParticipanteId(), dto.getAtividadeId());
            ctx.status(200).json(Map.of("mensagem", "Frequência via QR Code registrada com sucesso"));
        } catch (IllegalArgumentException | DomainRuleException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }

    public void consultarSituacao(Context ctx) {
        try {
            String participanteId = ctx.queryParam("participanteId");
            String atividadeId = ctx.queryParam("atividadeId");
            if (participanteId == null || atividadeId == null) {
                ctx.status(400).result("Os parâmetros participanteId e atividadeId são obrigatórios.");
                return;
            }
            boolean presente = calcularSituacaoPresencaPort.executar(participanteId, atividadeId);
            SituacaoPresencaDTO response = new SituacaoPresencaDTO(participanteId, atividadeId, presente);
            ctx.status(200).json(response);
        } catch (Exception e) {
            ctx.status(500).result("Erro ao consultar situação: " + e.getMessage());
        }
    }
}
