package br.ueg.eventos.application.usecase;

import br.ueg.eventos.application.dto.EventoDTO;
import br.ueg.eventos.application.port.in.CriarEventoPort;
import br.ueg.eventos.application.port.out.EventoRepositoryPort;
import br.ueg.eventos.application.port.out.UsuarioRepositoryPort;
import br.ueg.eventos.domain.exception.DomainRuleException;
import br.ueg.eventos.domain.model.*;

import java.util.UUID;

public class CriarEventoUseCase implements CriarEventoPort {

  private final EventoRepositoryPort eventoRepository;
  private final UsuarioRepositoryPort usuarioRepository;

  public CriarEventoUseCase(EventoRepositoryPort eventoRepository, UsuarioRepositoryPort usuarioRepository) {
    this.eventoRepository = eventoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public Evento executar(EventoDTO dto) {
    Usuario organizador = usuarioRepository.buscarPorId(dto.getIdOrganizador())
      .orElseThrow(() -> new DomainRuleException("O usuário organizador não foi encontrado!"));

    String idEvento = UUID.randomUUID().toString();

    Evento novoEvento = Evento.criarNovo(
        idEvento,
        organizador,
        dto.getTitulo(),
        dto.getDescricao(),
        dto.getTipo(),
        dto.getModalidade(),
        dto.getEndereco(),
        dto.getDataInicio(),
        dto.getDataFim()
        );

    return eventoRepository.salvar(novoEvento);
  }
}
