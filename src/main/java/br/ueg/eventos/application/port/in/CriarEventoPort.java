package br.ueg.eventos.application.port.in;

import br.ueg.eventos.application.dto.EventoDTO;
import br.ueg.eventos.domain.model.Evento;

public interface CriarEventoPort {
  Evento executar(EventoDTO dto);

}
