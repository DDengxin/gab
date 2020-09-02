package com.tengzhi.business.tooling.grantandrecover.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author 鱼游浅水
 * @create 2020-06-28
 */
public class MGzMjrecordPK implements Serializable {
    private static final long serialVersionUID = -172754250417288838L;
    private String mjNote;
    private String mjAct;
    private String mjCode;

    public MGzMjrecordPK() {
    }

    public MGzMjrecordPK(String mjNote, String mjAct, String mjCode) {
        this.mjNote = mjNote;
        this.mjAct = mjAct;
        this.mjCode = mjCode;
    }

    @Column(name = "mj_note", nullable = false, length = 255)
    @Id
    public String getMjNote() {
        return mjNote;
    }

    public void setMjNote(String mjNote) {
        this.mjNote = mjNote;
    }

    @Column(name = "mj_act", nullable = false, length = 255)
    @Id
    public String getMjAct() {
        return mjAct;
    }

    public void setMjAct(String mjAct) {
        this.mjAct = mjAct;
    }

    @Column(name = "mj_code", nullable = false, length = 255)
    @Id
    public String getMjCode() {
        return mjCode;
    }

    public void setMjCode(String mjCode) {
        this.mjCode = mjCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MGzMjrecordPK that = (MGzMjrecordPK) o;
        return Objects.equals(mjNote, that.mjNote) &&
                Objects.equals(mjAct, that.mjAct) &&
                Objects.equals(mjCode, that.mjCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mjNote, mjAct, mjCode);
    }
}
