package br.ueg.eventos.application.dto;

public class RegistroManualDTO {
    private String participanteId;
    private String atividadeId;
    private String responsavelId;
    public RegistroManualDTO() {}
    public String getParticipanteId() { return participanteId; }
    public void setParticipanteId(String participanteId) { this.participanteId = participanteId; }
    public String getAtividadeId() { return atividadeId; }
    public void setAtividadeId(String atividadeId) { this.atividadeId = atividadeId; }
    public String getResponsavelId() { return responsavelId; }
    public void setResponsavelId(String responsavelId) { this.responsavelId = responsavelId; }
}
