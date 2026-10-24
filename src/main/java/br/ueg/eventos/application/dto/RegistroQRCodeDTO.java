package br.ueg.eventos.application.dto;

public class RegistroQRCodeDTO {
    private String participanteId;
    private String atividadeId;
    public RegistroQRCodeDTO() {}
    public String getParticipanteId() { return participanteId; }
    public void setParticipanteId(String participanteId) { this.participanteId = participanteId; }
    public String getAtividadeId() { return atividadeId; }
    public void setAtividadeId(String atividadeId) { this.atividadeId = atividadeId; }
}
