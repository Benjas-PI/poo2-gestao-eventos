package br.ueg.eventos.application.dto;

public class SituacaoPresencaDTO {
    private String participanteId;
    private String atividadeId;
    private boolean presente;
    public SituacaoPresencaDTO() {}
    public SituacaoPresencaDTO(String participanteId, String atividadeId, boolean presente) {
        this.participanteId = participanteId;
        this.atividadeId = atividadeId;
        this.presente = presente;
    }
    public String getParticipanteId() { return participanteId; }
    public void setParticipanteId(String participanteId) { this.participanteId = participanteId; }
    public String getAtividadeId() { return atividadeId; }
    public void setAtividadeId(String atividadeId) { this.atividadeId = atividadeId; }
    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }
}
