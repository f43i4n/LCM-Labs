package de.ustutt.iaas.lcm.labs.model;

public class AcknowledgeMessage {

    public AcknowledgeMessage(boolean acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    /**
     * The acknowledgement of the message
     * True if is worked, false if not
     */
    private boolean acknowledgement;

    /*
        Getter & Setter
     */

    public boolean getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(boolean acknowledgement) {
        this.acknowledgement = acknowledgement;
    }
}
